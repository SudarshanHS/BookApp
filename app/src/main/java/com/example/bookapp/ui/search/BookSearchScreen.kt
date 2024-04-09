package com.example.bookapp.ui.search

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.bookapp.ui.common.DefaultTopAppBar
import com.example.bookapp.ui.theme.Purple40


@Composable
fun BookSearchScreen(navigationCallBack : (String, String?) -> Unit ) {

    val mContext = LocalContext.current

    Column(modifier = Modifier.fillMaxHeight()) {
        DefaultTopAppBar()
        Box(modifier = Modifier.fillMaxHeight()) {

            var searchText by remember { mutableStateOf(TextFieldValue("")) }
            var authorText by remember { mutableStateOf(TextFieldValue("")) }

            Column(modifier = Modifier.align(Alignment.Center)) {

                TextField(value = searchText,
                    onValueChange = { newValue -> searchText = newValue },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = { Text("Search Criteria") },
                    placeholder = { Text("Enter Book Name(Mandatory)") }
                )

                TextField(value = authorText,
                    onValueChange = { newValue -> authorText = newValue },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = { Text("Author ") },
                    placeholder = { Text("Enter Book Author") }
                )


            }
            Button(
                onClick = {



                    if(searchText.text.isEmpty()){
                            Toast.makeText(mContext, "Please Enter Search Criteria",
                                Toast.LENGTH_SHORT).show()

                          }else{
                              var authorName : String? = authorText.text

                              if(authorText.text.isEmpty()){
                                  authorName = null
                              }

                              navigationCallBack.invoke(searchText.text, authorName)
                          }

                },
                colors = ButtonDefaults.buttonColors(Purple40),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Purple40, shape = RectangleShape)
                    .align(Alignment.BottomCenter)

            ) {
                Text(text = "Submit", fontWeight = FontWeight.Bold)
            }


        }
    }

}