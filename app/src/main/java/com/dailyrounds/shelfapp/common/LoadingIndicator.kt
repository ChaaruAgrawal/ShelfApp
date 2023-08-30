package com.dailyrounds.shelfapp.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Show circular spinner while data is loading
 */
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    isDarkLoadingIndicator: Boolean = isSystemInDarkTheme()
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.background(
            color = if (isDarkLoadingIndicator) Color.Black else Color.White
        )
    ) {
        val infiniteTransition = rememberInfiniteTransition()

        val gradientColors = listOf(
            if (isDarkLoadingIndicator) Color.White else color,
            Color.Transparent
        )

        val rotateAnimation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 600,
                    easing = LinearEasing
                )
            ), label = ""
        )

        CircularProgressIndicator(
            modifier = Modifier
                .size(size = 32.dp)
                .rotate(degrees = rotateAnimation)
                .border(
                    width = 1.dp,
                    brush = Brush.sweepGradient(gradientColors),
                    shape = CircleShape
                ),
            progress = 1F,
            strokeWidth = 2.dp,
            color = Color.Transparent // Set background color
        )
    }
}