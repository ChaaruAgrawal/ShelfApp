package com.dailyrounds.shelfapp.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dailyrounds.shelfapp.data.models.User

@Dao
interface UsersDao {

    //for single user insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long


    //getting user data details
    @Query("select * from login where name Like :name")
    fun getUserDetails(name: String): User

}