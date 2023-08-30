package com.dailyrounds.shelfapp.ui.bookshelf

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dailyrounds.shelfapp.R

/**
 * View to show details of a book
 */
@Composable
fun BookDetailHome(modifier: Modifier = Modifier, bookShelfViewModel: BookShelfViewModel) {
    val bookViewed = bookShelfViewModel.book
    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier.padding(8.dp)) {
        BookItemView(book = bookViewed, bookShelfViewModel = bookShelfViewModel) {}
        Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier.padding(8.dp)) {
            Text(text = "Alias: " + bookViewed.alias)
            Text(text = "Updated on- " + bookViewed.lastChapterDate)
            Text(text = "Summary:")
            Text(text = stringResource(id = R.string.loren_ipsum))
        }
    }
}