package com.dailyrounds.shelfapp.ui.bookshelf

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dailyrounds.shelfapp.common.Resource
import com.dailyrounds.shelfapp.common.SortKey
import com.dailyrounds.shelfapp.data.models.Book
import com.dailyrounds.shelfapp.data.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model to render list of books
 * Handle user log out
 * maintains current book selected
 */
@HiltViewModel
class BookShelfViewModel @Inject constructor(private val booksRepository: BooksRepository): ViewModel() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val _books = MutableStateFlow<Resource<ArrayList<Book>>>(Resource.Initialized())
    val books = _books.asStateFlow()

    var book = Book()

    fun getAllBooks() {
        viewModelScope.launch {
            booksRepository.getBooks()
                .collect { books ->
                    books.data?.sortByDescending { it.title }
                    _books.emit(books)
                }
        }
    }

    fun sortBookShelf(key: SortKey, ascendingOrder: Boolean) {
        viewModelScope.launch {
            _books.value.data?.let { books ->
                if (ascendingOrder)
                    when (key) {
                        SortKey.TITLE -> books.sortBy { it.title }
                        SortKey.HITS -> books.sortBy { it.hits }
                        SortKey.FAVS -> books.sortBy { it.favorite.value }
                    }
                else
                    when (key) {
                        SortKey.TITLE -> books.sortByDescending { it.title }
                        SortKey.HITS -> books.sortByDescending { it.hits }
                        SortKey.FAVS -> books.sortByDescending { it.favorite.value }
                    }
                _books.emit(Resource.Success(books))
            }
        }
    }

}