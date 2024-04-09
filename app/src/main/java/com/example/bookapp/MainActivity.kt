package com.example.bookapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookapp.ui.details.BookDetailsScreen
import com.example.bookapp.ui.list.BookListScreen
import com.example.bookapp.ui.search.BookSearchScreen
import com.example.bookapp.ui.theme.BookAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BookApp()
                }
            }
        }
    }
}


@Composable
fun BookApp() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "destination_search") {


        composable(route = "destination_search") {
            BookSearchScreen { searchCriteria, authorName ->
                navController.navigate("destination_list/$searchCriteria/$authorName")
            }
        }

        composable(
            route = "destination_list/{search_criteria}/{author_name}",
            arguments = listOf(navArgument("search_criteria") {
                type = NavType.StringType
            },
                navArgument("author_name") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                })
        ) {
            BookListScreen(
                { navController.navigate("destination_list/$it") },
                {
                    navController.popBackStack()
                })
        }

        composable(
            route = "destination_list/{book_id}",
            arguments = listOf(navArgument("book_id") {
                type = NavType.StringType
            })
        ) {
            BookDetailsScreen {
                navController.popBackStack()
            }
        }

    }


}