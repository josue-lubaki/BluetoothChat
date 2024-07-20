package ca.josue_lubaki.bluetoothchat.mapper

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import ca.josue_lubaki.bluetoothchat.domain.chat.BluetoothDeviceDomain


@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain() = BluetoothDeviceDomain(
    name = name,
    address = address
)