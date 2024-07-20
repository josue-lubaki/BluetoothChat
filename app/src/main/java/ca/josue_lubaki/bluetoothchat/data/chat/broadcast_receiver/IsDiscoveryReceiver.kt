package ca.josue_lubaki.bluetoothchat.data.chat.broadcast_receiver

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class IsDiscoveryReceiver(
    private val isScanning: (isScanning: Boolean) -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
                isScanning(true)
            }
            BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                isScanning(false)
            }
        }
    }
}