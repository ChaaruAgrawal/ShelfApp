package com.dailyrounds.shelfapp.ui.authentication

import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dailyrounds.shelfapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * view model for user sign in
 */
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun validateUser(name: String, password: String): Boolean {
        val user = userRepository.verifyLoginUser(name)
        user?.let {
            if (user.name == name && user.password == password) {
                updateSharedPreferences(user.name)
                return true
            }
        }
        return false
    }

    private fun updateSharedPreferences(name: String) {
        val editor = sharedPreferences.edit()
        editor.putString("current_user", name)
        editor.apply()
    }

    fun isUserSignedIn(): MutableState<Boolean> {
        if (sharedPreferences.getString("current_user", null).isNullOrEmpty())
            return mutableStateOf(false)
        return mutableStateOf(true)
    }
}