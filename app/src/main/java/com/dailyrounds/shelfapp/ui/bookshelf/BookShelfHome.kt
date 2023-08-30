package com.dailyrounds.shelfapp.ui.bookshelf

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dailyrounds.shelfapp.common.ProcessAPI
import com.dailyrounds.shelfapp.common.Resource
import com.dailyrounds.shelfapp.data.models.Book

/**
 * Screen to show list of books with sorting menu and logout capabilities
 */
@Composable
fun BookShelfHome(
    modifier: Modifier = Modifier,
    bookShelfViewModel: BookShelfViewModel,
    navigateToBookDetail: () -> Unit,
    navigateToSignIn: () -> Unit
) {
    val booksList = bookShelfViewModel.books.collectAsState().value
    LaunchedEffect(key1 = Unit, block = {
        if (booksList is Resource.Initialized)
            bookShelfViewModel.getAllBooks()
    })
    BookShelfAppBar(modifier = modifier.shadow(2.dp), navigateToSignIn, bookShelfViewModel.sharedPreferences)
    ProcessAPI(
        state = booksList,
        handleSuccess = {
            Column(modifier = modifier.padding(top = 56.dp)) {
                SortingMenuView(bookShelfViewModel)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    booksList.data?.let {
                        items(it) { book ->
                            BookItemView(book, bookShelfViewModel, navigateToBookDetail)
                        }
                    }
                }
            }
        },
        handleError = {}
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookItemView(
    book: Book,
    bookShelfViewModel: BookShelfViewModel,
    navigateToBookDetail: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                bookShelfViewModel.book = book
                navigateToBookDetail()
            }
        ) {
            GlideImage(
                model = book.image,
                contentDescription = "book cover image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(44.dp)
            )
            Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(0.8f)) {
                Text(
                    text = book.title ?: ""
                )
                Text(text = "Hits: " + book.hits)
            }
        }
        Icon(
            imageVector = if (book.favorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "favorite book",
            modifier = Modifier.clickable {
                book.favorite.value = !book.favorite.value
            }
        )
    }
}
