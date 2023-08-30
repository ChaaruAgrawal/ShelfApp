package com.dailyrounds.shelfapp.data.remote

import com.dailyrounds.shelfapp.common.BOOKSHELF_URL
import com.dailyrounds.shelfapp.data.models.Book
import retrofit2.http.GET

/**
 * API interface to fetch books list
 */
interface BookShelfAPI {

    @GET(BOOKSHELF_URL)
    suspend fun getBooks(): ArrayList<Book>
}