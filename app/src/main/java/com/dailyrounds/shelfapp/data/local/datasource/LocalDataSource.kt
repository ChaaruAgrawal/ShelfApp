package com.dailyrounds.shelfapp.data.local.datasource

import com.dailyrounds.shelfapp.data.models.User

/**
 * Interactions with local data source
 */
interface LocalDataSource {

    fun insertUser(user: User)

    fun getUserDetails(name: String): User
}