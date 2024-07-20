package ca.josue_lubaki.bluetoothchat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.josue_lubaki.bluetoothchat.domain.chat.BluetoothController
import ca.josue_lubaki.bluetoothchat.domain.chat.BluetoothDevice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val bluetoothController: BluetoothController
) : ViewModel() {

    private val _state = MutableStateFlow(BluetoothState())
    val state = _state.asStateFlow()

    init {
        onCollectData()
    }

    private fun onCollectData() {
        viewModelScope.launch {
            combine(
                bluetoothController.scannedDevices,
                bluetoothController.pairedDevices,
                bluetoothController.isScanning
            ){
                scannedDevices, pairedDevices, isScanning ->
                   _state.update {
                      it.copy(
                          scannedDevices = scannedDevices,
                          pairedDevices = pairedDevices,
                          loading = isScanning
                      )
                   }
            }
            .flowOn(Dispatchers.IO)
            .distinctUntilChanged()
            .collect()
        }
    }

    fun onAction(action : BluetoothAction) {
        viewModelScope.launch(Dispatchers.IO) {
            dispatch(action)
        }
    }

    private fun dispatch(action : BluetoothAction) {
       when(action) {
           is BluetoothAction.StartScan -> {
               _state.update {
                  it.copy(
                      loading = true,
                      error = null
                  )
               }
               bluetoothController.startDiscovery()
               onCollectData()
           }
           is BluetoothAction.StopScan -> {
               bluetoothController.stopDiscovery()
               _state.update {
                   it.copy(
                       loading = false
                   )
               }
           }
       }
    }
}

data class BluetoothState(
    val scannedDevices : List<BluetoothDevice> = emptyList(),
    val pairedDevices : List<BluetoothDevice> = emptyList(),
    val loading : Boolean = false,
    val error : String? = null
)

sealed class BluetoothAction {
    data object StartScan : BluetoothAction()
    data object StopScan : BluetoothAction()
}