package com.dailyrounds.shelfapp.data.remote

import com.dailyrounds.shelfapp.common.Resource
import com.dailyrounds.shelfapp.data.models.Book

/**
 * fetch books list from remote data source
 */
interface RemoteDataSource {
    suspend fun getBooks(): Resource<ArrayList<Book>>
}