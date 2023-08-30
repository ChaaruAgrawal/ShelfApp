package com.dailyrounds.shelfapp.ui.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dailyrounds.shelfapp.data.models.Country
import com.dailyrounds.shelfapp.data.models.User

/**
 * Sign Up screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpHome(signUpViewModel: SignUpViewModel = hiltViewModel(), navigateToSignIn: () -> Unit) {
    val userName = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    val password = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    val confirmPassword = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    val passwordMatched = remember { mutableStateOf(true) }
    val selectedCountry = remember { mutableStateOf(Pair("", Country("Select", "select"))) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.height(56.dp))
        Row(modifier = Modifier.clickable { navigateToSignIn() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back to sign in")
            Text(text = "Back to Sign In")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Name")
        OutlinedTextField(value = userName.value, onValueChange = { userName.value = it}, modifier = Modifier.fillMaxWidth())
        Text(text = "Password")
        OutlinedTextField(value = password.value, onValueChange = { password.value = it}, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
        Text(text = "Confirm Password")
        OutlinedTextField(
            value = confirmPassword.value,
            onValueChange = {
                confirmPassword.value = it
                passwordMatched.value = (password.value == confirmPassword.value)
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Country")
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = selectedCountry.value.second.country,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .clickable {
                        expanded = false
                    }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                signUpViewModel.countries.forEach { (code, country) ->
                    DropdownMenuItem(
                        text = { Text(text = country.country) },
                        onClick = {
                            selectedCountry.value = Pair(code, country)
                            expanded = false
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            signUpViewModel.createUser(User(userName.value.text, password.value.text, selectedCountry.value.second.country))
            if (passwordMatched.value && signUpViewModel.error.value.isEmpty()) {
                signUpViewModel.error.value = ""
                navigateToSignIn()
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)) {
            Text(text = "SIGN UP", textAlign = TextAlign.Center)
        }
        if (passwordMatched.value.not()) {
            Text(text = "Passwords do not match", color = Color.Red)
        }
        if (signUpViewModel.error.value.isNotEmpty()) {
            Text(text = signUpViewModel.error.value, color = Color.Red)
        }
    }
}