package com.example.bookapp.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.bookapp.model.BookRepository
import com.example.bookapp.model.response.Book

class BookDetailsViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {


    private val bookRepository: BookRepository = BookRepository.getInstance()

    val bookResponse = mutableStateOf<Book?>(null)


    init {
        val bookId = savedStateHandle.get<String>("book_id") ?: ""
        val book: Book? = bookRepository.getBook(bookId)
        bookResponse.value = book
    }
}