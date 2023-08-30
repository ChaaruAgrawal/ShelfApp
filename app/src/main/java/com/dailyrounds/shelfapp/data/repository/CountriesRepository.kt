package com.dailyrounds.shelfapp.data.repository

import com.dailyrounds.shelfapp.data.models.Country

/**
 * fetch list of countries
 */
interface CountriesRepository {

    fun getCountries(): Map<String, Country>
}