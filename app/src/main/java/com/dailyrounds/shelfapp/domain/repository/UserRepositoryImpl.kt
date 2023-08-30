package com.dailyrounds.shelfapp.domain.repository

import com.dailyrounds.shelfapp.data.local.datasource.LocalDataSource
import com.dailyrounds.shelfapp.data.models.User
import com.dailyrounds.shelfapp.data.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource): UserRepository {

    override fun addUser(user: User) {
        localDataSource.insertUser(user)
    }

    override fun verifyLoginUser(name: String): User {
        return localDataSource.getUserDetails(name)
    }

}