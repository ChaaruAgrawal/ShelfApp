package com.dailyrounds.shelfapp.ui.bookshelf

import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Top bar visible for BookShelf Screen
 */
@Composable
fun BookShelfAppBar(
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit,
    sharedPreferences: SharedPreferences
) {
    Row(
        modifier = modifier.height(56.dp).fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Your Bookshelf")
        Icon(
            imageVector = Icons.Default.ExitToApp,
            contentDescription = "sign out",
            modifier = modifier.clickable {
                val editor = sharedPreferences.edit()
                editor.putString("current_user", "")
                editor.apply()
                navigateToSignIn()
            }
        )
    }
}