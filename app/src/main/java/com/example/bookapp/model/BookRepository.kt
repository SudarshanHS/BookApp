package com.example.bookapp.model

import com.example.bookapp.model.api.BookWebService
import com.example.bookapp.model.response.Book

class BookRepository(private val webService: BookWebService= BookWebService()) {

    private var cachedBooks = listOf<Book>()


    suspend fun getBooks(searchCriteria : String, author :String):List<Book>{
        val resposne = webService.getBook(searchCriteria, author).bookList
        cachedBooks = resposne
        return resposne
    }


    fun  getBook(id:String):Book?{
        return cachedBooks.firstOrNull{
            it.id == id
        }
    }

    companion object{

        @Volatile
        private var instance : BookRepository ? = null

        fun getInstance() = instance ?: synchronized(this){
            instance ?: BookRepository().also { instance = it }
        }

    }

}