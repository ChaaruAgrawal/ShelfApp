package com.dailyrounds.shelfapp.domain.repository

import android.content.Context
import com.dailyrounds.shelfapp.data.models.Country
import com.dailyrounds.shelfapp.data.repository.CountriesRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(private val context: Context): CountriesRepository {

    override fun getCountries(): Map<String, Country> {
        val inputStream = context.assets.open("countries.json")
        val reader = InputStreamReader(inputStream)
        val listType = object : TypeToken<Map<String, Country>>() {}.type
        return Gson().fromJson(reader, listType)
    }

}