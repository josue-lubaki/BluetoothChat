package ca.josue_lubaki.bluetoothchat.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.josue_lubaki.bluetoothchat.ui.theme.BluetoothChatTheme

@Composable
fun ScanButton(
    modifier: Modifier = Modifier,
    loading: Boolean,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit
) {
    Box(
        modifier = modifier.padding(horizontal = 4.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center,
    ) {

        val animateColorState = animateColorAsState(targetValue = if(loading) Color(0xFFC62828) else Color(0xFF25661E),
            label = "animateColorState"
        )

        Button(
            modifier = Modifier.height(50.dp),
            onClick = {
                if (loading) {
                    onStopScan()
                } else {
                    onStartScan()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = animateColorState.value,
                contentColor = Color.White
            )
        ){
            Text(
                text = if(loading) "Stop Scan" else "Start Scan",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScanButtonTruePreview() {
    BluetoothChatTheme {
        ScanButton(
            loading = true,
            onStartScan = {},
            onStopScan = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScanButtonFalsePreview() {
    BluetoothChatTheme {
        ScanButton(
            loading = false,
            onStartScan = {},
            onStopScan = {}
        )
    }
}