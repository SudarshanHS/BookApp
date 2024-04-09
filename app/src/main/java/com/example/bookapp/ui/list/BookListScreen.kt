package com.example.bookapp.ui.list

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.bookapp.model.response.Book
import com.example.bookapp.ui.common.BookTopAppBar
import com.example.bookapp.ui.common.RatingBar

@Composable
fun BookListScreen(callBack: (String) -> Unit, onBackPress: () -> Unit) {


    val viewModel: BookListViewModel = viewModel()

    val searchCriteria = viewModel.getSearchCriteria.value
    val authorName = viewModel.getAuthorName.value

    val books = viewModel.bookState.value

    Column {
        BookTopAppBar(title = "Book List") {
            onBackPress.invoke()
        }

        Text(
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold,
            text = "Showing result for Search Criteraia : $searchCriteria and Author : $authorName"
        )

        LazyColumn {
            items(books) { book ->
                BookData(book, callBack)

            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookData(book: Book, callBack: (String) -> Unit) {

    Card(
        onClick = { callBack.invoke(book.id) },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Column(Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .animateContentSize()
                    .padding(8.dp)
            ) {
                var image = book.volumeInfo.imageLinks?.thumbnail
                    ?: "https://images6.alphacoders.com/488/thumb-1920-488158.jpg"
                image = image.replace("http:", "https:")

                AsyncImage(
                    model = image,
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(height = 100.dp, width = 88.dp)
                        .fillMaxHeight()
                )

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = book.volumeInfo.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = book.volumeInfo.authors?.firstOrNull() ?: "No Data!",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Light
                    )

                    val rating: Double? = book.volumeInfo.averageRating
                    rating?.let {
                        RatingBar(rating = rating)
                    }

                }

                Text(
                    text = book.volumeInfo.description ?: "",
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal
                )

            }
        }

    }
}
