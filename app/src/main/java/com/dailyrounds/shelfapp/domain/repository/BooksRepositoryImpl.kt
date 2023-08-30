package com.dailyrounds.shelfapp.domain.repository

import com.dailyrounds.shelfapp.common.Resource
import com.dailyrounds.shelfapp.data.models.Book
import com.dailyrounds.shelfapp.data.remote.RemoteDataSource
import com.dailyrounds.shelfapp.data.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): BooksRepository {

    override suspend fun getBooks(): Flow<Resource<ArrayList<Book>>> {
        return flow {
            emit(Resource.Loading())
            val response = remoteDataSource.getBooks()
            response.data?.let {
                if (it.isNotEmpty())
                    emit(Resource.Success(it))
            } ?: kotlin.run {
                emit(Resource.Error(message = response.message ?: "SOMETHING WENT WRONG"))
            }
        }
    }

}