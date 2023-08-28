package ecb.manifest.kowalski.obd_scan.ui.presentation

import ecb.manifest.kowalski.obd_scan.bluetooth.BluetoothDeviceDomain

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDeviceDomain> = emptyList(),
    val pairedDevices: List<BluetoothDeviceDomain> = emptyList(),
)