package com.dailyrounds.shelfapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
data class User (
    @PrimaryKey val name: String,
    val password: String,
    val country: String
)