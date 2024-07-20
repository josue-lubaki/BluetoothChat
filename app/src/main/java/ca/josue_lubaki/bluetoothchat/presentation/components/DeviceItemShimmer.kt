package ca.josue_lubaki.bluetoothchat.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ca.josue_lubaki.bluetoothchat.ui.theme.BluetoothChatTheme

@Composable
fun DeviceItemShimmer(
    modifier: Modifier = Modifier,
    alpha: Float,
    widthFraction: Float = 1f,
    heightSurface: Dp = 24.dp,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    Surface(
        modifier = modifier
            .alpha(alpha = alpha)
            .fillMaxWidth(widthFraction)
            .height(heightSurface),
        color = color.copy(alpha = 0.2f),
        shape = RoundedCornerShape(16.dp),
    ){}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DeviceItemShimmerPreview() {
    BluetoothChatTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            AnimateShimmerEffect {
                DeviceItemShimmer(
                    alpha = it,
                    widthFraction = 0.5f,
                )
            }
        }
    }
}