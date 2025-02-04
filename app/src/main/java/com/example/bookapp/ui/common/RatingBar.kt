package com.example.bookapp.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.math.ceil
import kotlin.math.floor


@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = "", tint = starsColor)
        }

        if (halfStar) {
            Icon(imageVector = Icons.Outlined.StarHalf, contentDescription = "", tint = starsColor)
        }

        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline, contentDescription = "", tint = starsColor
            )

        }
    }

}
