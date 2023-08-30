package com.dailyrounds.shelfapp.data.repository

import com.dailyrounds.shelfapp.common.Resource
import com.dailyrounds.shelfapp.data.models.Book
import kotlinx.coroutines.flow.Flow

/**
 * fetch all books
 */
interface BooksRepository {

    suspend fun getBooks(): Flow<Resource<ArrayList<Book>>>
}