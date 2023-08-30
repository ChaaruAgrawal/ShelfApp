package com.dailyrounds.shelfapp.ui.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dailyrounds.shelfapp.data.models.User
import com.dailyrounds.shelfapp.data.repository.CountriesRepository
import com.dailyrounds.shelfapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * view model for creating a new user
 */
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val countries = countriesRepository.getCountries()

    val error = mutableStateOf("")

    fun createUser(user: User) {
        if (validCredentials(user))
            userRepository.addUser(user)
    }

    private fun validCredentials(user: User): Boolean {
        val passwordPattern =
            Regex("^(?=.*[0-9])(?=.*[!@#\$%&()\\[\\]])(?=.*[a-z])(?=.*[A-Z]).{8,}$")

        if (user.password.isEmpty()) {
            error.value = "Password is required"
            return false
        }

        if (!user.password.matches(passwordPattern)) {
            error.value = "Password must contain at least:" +
                    "\n one digit" +
                    "\n one special character" +
                    "\n one lowercase letter" +
                    "\n one uppercase letter" +
                    "\n 8 characters"
            return false
        }

        if (user.name.isEmpty()){
            error.value = "Name cannot be empty"
            return false
        }

        if (user.name == userRepository.verifyLoginUser(user.name).name) {
            error.value = "User already exists"
            return false
        }

        return true
    }

}