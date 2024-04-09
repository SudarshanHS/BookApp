package com.example.bookapp.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.bookapp.model.response.Book
import com.example.bookapp.ui.common.BookTopAppBar
import com.example.bookapp.ui.common.RatingBar
import com.example.bookapp.ui.theme.Purple80

@Composable
fun BookDetailsScreen(callBack: () -> Unit) {

    val viewModel: BookDetailsViewModel = viewModel()

    viewModel.bookResponse.value?.let { book ->
        BookDetails(book, callBack)
    }
}


@Composable
private fun BookDetails(book: Book, callBack: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

        BookTopAppBar(title = "Book Details") {
            callBack.invoke()
        }

        Box(
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Card {
                Column(
                    modifier = Modifier
                        .background(Purple80)
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {

                    var image = book.volumeInfo.imageLinks?.thumbnail
                        ?: "https://images6.alphacoders.com/488/thumb-1920-488158.jpg"
                    image = image.replace("http:", "https:")

                    AsyncImage(
                        model = image,
                        contentDescription = "",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .size(height = 200.dp, width = 150.dp)
                            .fillMaxHeight()
                            .padding(top = 32.dp)
                            .align(CenterHorizontally)
                    )


                    Text(
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(top = 16.dp, bottom = 4.dp),
                        text = book.volumeInfo.authors?.firstOrNull() ?: "",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = book.volumeInfo.title,
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(bottom = 16.dp)
                    )

                    Text(text = book.volumeInfo.publisher ?: "")

                    val rating: Double? = book.volumeInfo.averageRating
                    val ratingCount: Int? = book.volumeInfo.ratingCount

                    Row(modifier = Modifier.padding(bottom = 8.dp)) {

                        rating?.let {
                            RatingBar(rating = rating)
                        }

                        ratingCount?.let {
                            Text(text = "($it)")
                        }
                    }

                    Text(
                        text = book.volumeInfo.description ?: "",
                        modifier = Modifier.padding(bottom = 16.dp),
                    )


                }
            }
        }
    }
}