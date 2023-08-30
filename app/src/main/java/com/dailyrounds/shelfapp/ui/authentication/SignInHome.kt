package com.dailyrounds.shelfapp.ui.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Sign In Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInHome(signInViewModel: SignInViewModel = hiltViewModel(), navigateToBookShelf: () -> Unit, navigateToSignUp: () -> Unit) {
    if (signInViewModel.isUserSignedIn().value)
        navigateToBookShelf()

    val userName = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    val password = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    val authenticationSuccess = remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.height(56.dp))
        Text(text = "Name")
        OutlinedTextField(value = userName.value, onValueChange = { userName.value = it}, modifier = Modifier.fillMaxWidth())
        Text(text = "Password")
        OutlinedTextField(value = password.value, onValueChange = { password.value = it}, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
        if (authenticationSuccess.value.not())
            Text(text = "Invalid Credentials", color = Color.Red)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (signInViewModel.validateUser(userName.value.text, password.value.text)) {
                authenticationSuccess.value = true
                navigateToBookShelf()
            }
            else
                authenticationSuccess.value = false
        }, modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)) {
            Text(text = "SIGN IN", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "New User? Sign up here.", modifier = Modifier.clickable { navigateToSignUp() })
    }
}