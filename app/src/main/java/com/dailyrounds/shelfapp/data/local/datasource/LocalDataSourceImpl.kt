package com.dailyrounds.shelfapp.data.local.datasource

import com.dailyrounds.shelfapp.data.local.room.UsersDao
import com.dailyrounds.shelfapp.data.models.User

class LocalDataSourceImpl(private val usersDao: UsersDao) : LocalDataSource {

    override fun insertUser(user: User) {
        usersDao.insertUser(user)
    }

    override fun getUserDetails(name: String): User {
        return usersDao.getUserDetails(name)
    }

}
