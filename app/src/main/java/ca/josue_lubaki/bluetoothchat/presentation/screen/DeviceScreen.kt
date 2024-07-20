package ca.josue_lubaki.bluetoothchat.presentation.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ca.josue_lubaki.bluetoothchat.domain.chat.BluetoothDevice
import ca.josue_lubaki.bluetoothchat.presentation.BluetoothState
import ca.josue_lubaki.bluetoothchat.presentation.components.AnimateShimmerEffect
import ca.josue_lubaki.bluetoothchat.presentation.components.DeviceItem
import ca.josue_lubaki.bluetoothchat.presentation.components.DeviceItemShimmer
import ca.josue_lubaki.bluetoothchat.presentation.components.ErrorText
import ca.josue_lubaki.bluetoothchat.presentation.components.ScanButton
import ca.josue_lubaki.bluetoothchat.presentation.components.SectionTitle

@Composable
fun DeviceScreen(
    state : BluetoothState,
    onStartScan : () -> Unit,
    onStopScan : () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
//        BluetoothDevicesContent(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 32.dp)
//                .weight(1f),
//            state = state
//        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp)
            .weight(1f)
        ) {
            BluetoothDeviceList(
                state = state,
                onClick = {
                    // TODO: Handle click
                },
            )
        }

        ScanButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            loading = state.loading,
            onStartScan = onStartScan,
            onStopScan = onStopScan
        )
    }
}

//@Composable
//private fun BluetoothDevicesContent(
//    modifier: Modifier = Modifier,
//    state: BluetoothState
//) {
//    Box(modifier = modifier) {
//        BluetoothDeviceList(
//            state = state,
//            onClick = {
//                // TODO: Handle click
//            },
//        )
//    }
//}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BluetoothDeviceList(
    modifier: Modifier = Modifier,
    state: BluetoothState,
    onClick : (BluetoothDevice) -> Unit
) {

    val pairedDevices = remember(state.pairedDevices) { state.pairedDevices }
    val scannedDevices = remember(state.scannedDevices) { state.scannedDevices }
    val isLoading = remember(state.loading) { state.loading }

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            SectionTitle(title = "Paired Devices")
        }

        if (pairedDevices.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ){
                    Text(
                        text = "No paired devices found",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }

        items(pairedDevices, key = { it.address }) { device ->
            DeviceItem(
                modifier = Modifier.animateItemPlacement(
                    animationSpec = spring(
                        dampingRatio = Spring.StiffnessVeryLow,
                        stiffness = Spring.StiffnessLow
                    )
                ),
                device = device,
                onClick = onClick
            )
        }

        item {
            SectionTitle(title = "Scanned Devices")
        }

        items(scannedDevices, key = { it.address }) { device ->
            DeviceItem(
                modifier = Modifier.animateItemPlacement(
                    animationSpec = spring(
                        dampingRatio = Spring.StiffnessVeryLow,
                        stiffness = Spring.StiffnessLow
                    )
                ),
                device = device,
                onClick = onClick
            )
        }

        if(isLoading) {
            item {
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ){
                    AnimateShimmerEffect {
                        DeviceItemShimmer(
                            alpha = it,
                            widthFraction = 0.15f
                        )
                    }
                }
            }
        }

        if(state.error?.isNotEmpty() == true) {
            item {
                ErrorText(error = state.error)
            }
        }
    }
}