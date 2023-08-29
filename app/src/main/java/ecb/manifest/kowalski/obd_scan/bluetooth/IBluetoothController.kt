package ecb.manifest.kowalski.obd_scan.bluetooth

import android.bluetooth.BluetoothSocket
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface IBluetoothController {
    val bluetoothSocket: BluetoothSocket?

    val isConnected: StateFlow<Boolean>
    val scannedDevices: StateFlow<List<BluetoothDeviceDomain>>
    val pairedDevices: StateFlow<List<BluetoothDeviceDomain>>
    val errors: SharedFlow<String>

    fun startDiscovery()
    fun stopDiscovery()

    fun connectToDevice(device: BluetoothDeviceDomain): Flow<IConnectionResult>

    fun closeConnection()
    fun release()
}