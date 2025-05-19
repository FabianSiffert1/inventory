package io.siffert.mobile.app.inventory.core.designsystem.util

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.loadingAnimationBackground() = run {
    val shimmerColors =
        listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.3f),
            Color.LightGray.copy(alpha = 0.6f),
        )

    val transition = rememberInfiniteTransition(label = "loading_shimmer")
    val translateAnim =
        transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec =
                infiniteRepeatable(
                    animation = tween(durationMillis = 1500, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart,
                ),
            label = "loading_shimmer_animation",
        )

    val brush =
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(translateAnim.value - 1000f, 0f),
            end = Offset(translateAnim.value, 0f),
        )
    then(Modifier.background(brush))
}
