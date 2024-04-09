package com.example.bookapp.ui.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.model.BookRepository
import com.example.bookapp.model.response.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookListViewModel(
    private val savedStateHandle: SavedStateHandle
)  : ViewModel(){

    private val _getSearchCriteria  = MutableLiveData("")
    val getSearchCriteria : LiveData<String> = _getSearchCriteria

    private val _getAuthorName  = MutableLiveData("")
    val getAuthorName : LiveData<String> = _getAuthorName


    private val bookRepository = BookRepository.getInstance()

    val bookState : MutableState<List<Book>> = mutableStateOf(emptyList<Book>())

    init {
        val searchCriteria = savedStateHandle.get<String>("search_criteria") ?: ""
        val authorName = savedStateHandle.get<String>("author_name") ?: ""

        _getSearchCriteria.value = searchCriteria
        _getAuthorName.value = authorName

        viewModelScope.launch(Dispatchers.IO) {
            getBookList(searchCriteria,authorName)
        }

    }

    private suspend fun  getBookList(searchCriteria: String, authorName:String){
        val bookList = bookRepository.getBooks(searchCriteria,authorName)
        bookState.value = bookList
    }

}