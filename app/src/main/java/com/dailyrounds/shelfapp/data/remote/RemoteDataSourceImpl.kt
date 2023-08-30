package com.dailyrounds.shelfapp.data.remote

import com.dailyrounds.shelfapp.common.Resource
import com.dailyrounds.shelfapp.data.models.Book
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val bookShelfAPI: BookShelfAPI) : RemoteDataSource {

    override suspend fun getBooks(): Resource<ArrayList<Book>> {
        val response = bookShelfAPI.getBooks()
        return if (response.isNotEmpty())
            Resource.Success(data = response)
        else Resource.Error("SOMETHING WENT WRONG")
    }

}