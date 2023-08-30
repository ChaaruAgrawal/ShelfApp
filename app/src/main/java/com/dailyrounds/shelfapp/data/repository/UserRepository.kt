package com.dailyrounds.shelfapp.data.repository

import com.dailyrounds.shelfapp.data.models.User

/**
 * User interactions with local database
 */
interface UserRepository {

    fun addUser(user: User)

    fun verifyLoginUser(name: String): User
}