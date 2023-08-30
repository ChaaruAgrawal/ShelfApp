package com.dailyrounds.shelfapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.dailyrounds.shelfapp.common.BASE_URL
import com.dailyrounds.shelfapp.data.local.datasource.LocalDataSource
import com.dailyrounds.shelfapp.data.local.datasource.LocalDataSourceImpl
import com.dailyrounds.shelfapp.data.local.room.BookShelfDatabase
import com.dailyrounds.shelfapp.data.local.room.BookShelfDatabase.Companion.DATABASE_NAME
import com.dailyrounds.shelfapp.data.local.room.UsersDao
import com.dailyrounds.shelfapp.data.remote.BookShelfAPI
import com.dailyrounds.shelfapp.data.remote.RemoteDataSource
import com.dailyrounds.shelfapp.data.remote.RemoteDataSourceImpl
import com.dailyrounds.shelfapp.data.repository.BooksRepository
import com.dailyrounds.shelfapp.data.repository.CountriesRepository
import com.dailyrounds.shelfapp.data.repository.UserRepository
import com.dailyrounds.shelfapp.domain.repository.BooksRepositoryImpl
import com.dailyrounds.shelfapp.domain.repository.CountriesRepositoryImpl
import com.dailyrounds.shelfapp.domain.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBookShelfApiService(retrofit: Retrofit): BookShelfAPI {
        return retrofit.create(BookShelfAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(dataSource: RemoteDataSourceImpl): RemoteDataSource = dataSource

    @Provides
    @Singleton
    fun provideBooksRepository(repository: BooksRepositoryImpl): BooksRepository = repository

    @Provides
    @Singleton
    fun provideCountriesRepository(repository: CountriesRepositoryImpl): CountriesRepository = repository

    @Provides
    @Singleton
    fun provideUsersRepository(repository: UserRepositoryImpl): UserRepository = repository

    @Singleton
    @Provides
    fun provideApplicationContextUtil(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Singleton
    @Provides
    fun provideBookShelfDatabase(@ApplicationContext context: Context): BookShelfDatabase =
        Room.databaseBuilder(
            context,
            BookShelfDatabase::class.java,
            DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration() // allows database to be cleared after upgrading version
            .build()

    @Singleton
    @Provides
    fun provideDatabaseHelper(bookShelfDatabase: BookShelfDatabase): LocalDataSource {
        return LocalDataSourceImpl(bookShelfDatabase.userDao())
    }

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

}