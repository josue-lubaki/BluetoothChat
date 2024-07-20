package ca.josue_lubaki.bluetoothchat.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.josue_lubaki.bluetoothchat.domain.chat.BluetoothDevice
import ca.josue_lubaki.bluetoothchat.ui.theme.BluetoothChatTheme

@Composable
fun DeviceItem(
    modifier: Modifier = Modifier,
    device: BluetoothDevice,
    onClick: (BluetoothDevice) -> Unit
) {
    Text(
        text = device.name ?: device.address,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(device) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun DeviceItemPreview() {
    BluetoothChatTheme {
        DeviceItem(
            device = BluetoothDevice("device", "address"),
            onClick = {}
        )
    }
}