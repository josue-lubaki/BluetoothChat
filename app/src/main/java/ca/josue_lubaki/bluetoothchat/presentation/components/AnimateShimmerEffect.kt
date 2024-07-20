package ca.josue_lubaki.bluetoothchat.presentation.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable

@Composable
fun AnimateShimmerEffect(
    durationMillis : Int = 400,
    delayMillis : Int = 50,
    shimmerScreen: @Composable (alpha: Float) -> Unit
){
    val transition = rememberInfiniteTransition(label = "AlphaTransition")
    val alphaAnim = transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                delayMillis = delayMillis,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alphaAnim"
    )

    shimmerScreen(alphaAnim.value)
}