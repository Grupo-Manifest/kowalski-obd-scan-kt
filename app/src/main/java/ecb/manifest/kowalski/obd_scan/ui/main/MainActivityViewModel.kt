package ecb.manifest.kowalski.obd_scan.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ecb.manifest.kowalski.obd_scan.bluetooth.IBluetoothController
import ecb.manifest.kowalski.obd_scan.ui.presentation.BluetoothUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val bluetoothController: IBluetoothController
) : ViewModel() {
    private val _state = MutableStateFlow(BluetoothUiState())
    val state = combine(
        bluetoothController.scannedDevices,
        bluetoothController.pairedDevices,
        _state
    ) { scannedDevices, pairedDevices, state ->
        state.copy(
            scannedDevices = scannedDevices,
            pairedDevices = pairedDevices,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)
}