package com.example.bookapp.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTopAppBar(
    title: String,
    imageVector: ImageVector = Icons.Filled.ArrowBack,
    callBack: () -> Unit
) {
    TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary
    ), title = { Text(title) },
        navigationIcon = { NavigationIcon(imageVector, callBack) }

    )
}


@Composable
fun NavigationIcon(imageVector: ImageVector = Icons.Filled.ArrowBack, callBack: () -> Unit) {
    IconButton(onClick = { callBack.invoke() }) {
        Icon(imageVector = imageVector, contentDescription = "")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar() {
    TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary
    ), title = { Text("Book App") })
}