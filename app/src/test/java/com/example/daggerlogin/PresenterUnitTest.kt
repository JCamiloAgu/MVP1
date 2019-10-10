package com.example.daggerlogin

import com.example.daggerlogin.login.LoginActivityMVP
import com.example.daggerlogin.login.LoginActivityPresenter
import com.example.daggerlogin.login.User
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


class PresenterUnitTest {

    lateinit var presenter: LoginActivityPresenter
    lateinit var user: User

    lateinit var mockedView: LoginActivityMVP.View
    lateinit var mockedModel: LoginActivityMVP.Model

    @Before
    fun init()
    {
        mockedModel = mock(LoginActivityMVP.Model::class.java)
        mockedView = mock(LoginActivityMVP.View::class.java)

        user = User(0, "Camilo", "Agudelo")

//        `when`(mockedModel.getUser()).thenReturn(user)
//
//        `when`(mockedView.getFirstName()).thenReturn("Camilo")
//        `when`(mockedView.getLastName()).thenReturn("Agudelo")

        presenter = LoginActivityPresenter(mockedModel)
        presenter.setView(mockedView)
    }

    @Test
    fun noExistInteractionWithModel()
    {
        presenter.getCurrentUser()

        verify(mockedView, times(1)).showUserNotAvailable()
    }

    @Test
    fun loadUserFromTheRepositoryWhenValidUserIsPresent()
    {
        `when`(mockedModel.getUser()).thenReturn(user)

        presenter.getCurrentUser()

        //Se verifica la interacción con el modelo de datos.
        verify(mockedModel, times(1)).getUser()

        //Se verifica la interacción con la vista.
        verify(mockedView, times(1)).setFirstName("Camilo")
        verify(mockedView, times(1)).setLastName("Agudelo")
        verify(mockedView, never()).showUserNotAvailable()
    }

    @Test
    fun showErrorMessageWhenUserIsNull()
    {
        `when`(mockedModel.getUser()).thenReturn(null)

        presenter.getCurrentUser()

        //Se verifica la interacción con el modelo de datos.
        verify(mockedModel, times(1)).getUser()

        //Se verifica la interacción con la vista.
        verify(mockedView, never()).setFirstName("")
        verify(mockedView, never()).setLastName("")
        verify(mockedView, times(1)).showUserNotAvailable()
    }

    @Test
    fun createErrorMessageIfAnyFieldIsEmpty()
    {
        `when`(mockedView.getFirstName()).thenReturn("")

        presenter.loginButtonClicked()

        verify(mockedView, times(1)).getFirstName()
        verify(mockedView, never()).getLastName()
        verify(mockedView, times(1)).showInputError()

        `when`(mockedView.getFirstName()).thenReturn("Camilo")
        `when`(mockedView.getLastName()).thenReturn("")

        presenter.loginButtonClicked()

        verify(mockedView, times(2)).getFirstName()
        verify(mockedView, times(1)).getLastName()
        verify(mockedView, times(2)).showInputError()//El método se llama antes y ahora lo hace otra vez, es un total de dos (2) veces
    }

    @Test
    fun saveValidUser()
    {
        `when`(mockedView.getFirstName()).thenReturn("Camilo")
        `when`(mockedView.getLastName()).thenReturn("Agudelo")

        presenter.loginButtonClicked()

        verify(mockedModel, times(1)).createUser("Camilo", "Agudelo")

        verify(mockedView, times(2)).getFirstName()
        verify(mockedView, times(2)).getLastName()

        verify(mockedView, times(1)).showUserSave()
    }
}