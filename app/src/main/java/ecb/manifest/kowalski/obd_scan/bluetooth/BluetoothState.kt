package ecb.manifest.kowalski.obd_scan.bluetooth

import ecb.manifest.kowalski.obd_scan.bluetooth.BluetoothDeviceDomain

data class BluetoothState(
    val scannedDevices: List<BluetoothDeviceDomain> = emptyList(),
    val pairedDevices: List<BluetoothDeviceDomain> = emptyList(),
    val isConnected: Boolean = false,
    val isConnecting: Boolean = false,
    val errorMessage: String? = null,
)