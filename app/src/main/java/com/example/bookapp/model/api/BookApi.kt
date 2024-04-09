package com.example.bookapp.model.api

import com.example.bookapp.model.response.BookResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class BookWebService {


    private var api: BookApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(BookApi::class.java)
    }


    suspend fun getBook(searchCriteria: String, author: String): BookResult {
        return api.getBookList(searchCriteria, author)
    }

    interface BookApi {

        @GET("volumes/")
        suspend fun getBookList(
            @Query("q") searchCriteria: String,
            @Query("inauthor") author: String
        ): BookResult

    }

}

///https://www.googleapis.com/books/v1/volumes?q=harry?inauthor=rowling