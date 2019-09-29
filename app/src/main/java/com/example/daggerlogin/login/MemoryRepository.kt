package com.example.daggerlogin.login

class MemoryRepository : LoginRepository {

    private var user: User? = null

    override fun saveUser(user: User) {
        this.user = getUser()
        this.user = user
    }

//    override fun getUser(): User = run {
//        user = User(0, "Antonio", "Banderas")
//        user
//    }

    override fun getUser(): User{
        if(user == null) {
            user = User(0, "Antonio", "Banderas")
            return user!!
        }
        return user!!
    }
}
