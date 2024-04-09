package com.example.bookapp.model.response

import android.health.connect.datatypes.units.Volume
import com.google.gson.annotations.SerializedName

data class BookResult(
    @SerializedName("items") val bookList : List<Book>
)


data class Book(
    @SerializedName("id") val id : String,
    @SerializedName("volumeInfo") val volumeInfo : VolumeInfo
)


data class VolumeInfo(
    @SerializedName("title") val title : String,
    @SerializedName("description") val description: String?,
    @SerializedName("averageRating") val averageRating: Double?,
    @SerializedName("ratingCount") val ratingCount: Int?,
    @SerializedName("publisher") val publisher: String?,
    @SerializedName("imageLinks") val imageLinks: ImageLink?,
    @SerializedName("authors") val authors: List<String>,

)

data class ImageLink(
    @SerializedName("thumbnail") val thumbnail : String?
)

