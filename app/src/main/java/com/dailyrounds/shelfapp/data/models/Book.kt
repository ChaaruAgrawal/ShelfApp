package com.dailyrounds.shelfapp.data.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Book(
    val id: String? = null,
    val image: String? = null,
    val hits: Int? = null,
    val alias: String? = null,
    val title: String? = null,
    val lastChapterDate: Int? = null,
    var favorite: MutableState<Boolean> = mutableStateOf(false)
)
