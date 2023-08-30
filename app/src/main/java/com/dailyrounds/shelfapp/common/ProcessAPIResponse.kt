package com.dailyrounds.shelfapp.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Maintain UI according to state of objects
 */
@Composable
fun <T> ProcessAPI(
    state: Resource<T>,
    handleSuccess: @Composable () -> Unit,
    handleError: @Composable () -> Unit
) {
    when (state) {
        is Resource.Loading -> {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }
        is Resource.Success -> {
            handleSuccess()
        }
        is Resource.Error -> {
            handleError()
        }
        else -> {}
    }
}