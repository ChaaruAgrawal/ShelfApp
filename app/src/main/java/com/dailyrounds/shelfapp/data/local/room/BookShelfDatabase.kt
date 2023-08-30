package com.dailyrounds.shelfapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dailyrounds.shelfapp.data.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class BookShelfDatabase : RoomDatabase() {
    abstract fun userDao(): UsersDao

    companion object {
        const val DATABASE_NAME = "bookshelf_db"
    }
}