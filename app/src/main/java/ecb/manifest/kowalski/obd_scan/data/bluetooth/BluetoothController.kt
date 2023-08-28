package ecb.manifest.kowalski.obd_scan.data.bluetooth

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import ecb.manifest.kowalski.obd_scan.bluetooth.BluetoothDeviceDomain
import ecb.manifest.kowalski.obd_scan.bluetooth.IBluetoothController
import ecb.manifest.kowalski.obd_scan.bluetooth.IConnectionResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import java.io.IOException
import java.util.UUID

@SuppressLint("MissingPermission")
class BluetoothController(private val context: Context) : IBluetoothController {
    private val bluetoothManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val _scannedDevices = MutableStateFlow<List<BluetoothDeviceDomain>>(emptyList())
    override val scannedDevices: StateFlow<List<BluetoothDeviceDomain>>
        get() = _scannedDevices.asStateFlow()

    private val _pairedDevices = MutableStateFlow<List<BluetoothDeviceDomain>>(emptyList())
    override val pairedDevices: StateFlow<List<BluetoothDeviceDomain>>
        get() = _pairedDevices.asStateFlow()

    private val foundDeviceReceiver= FoundDeviceReceiver {device ->
        _scannedDevices.update { devices ->
            val newDevice = device.toBlueoothDeviceDomain()
            if (newDevice in devices) devices else (devices + newDevice)
        }
    }

    private var bluetoothSocket: BluetoothSocket? = null

    init {
        updatePairedDevices()
    }

    override fun startDiscovery() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_SCAN)) return

        context.registerReceiver(
            foundDeviceReceiver,
            IntentFilter(BluetoothDevice.ACTION_FOUND)
        )

        updatePairedDevices()

        bluetoothAdapter?.startDiscovery()
    }

    override fun stopDiscovery() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_SCAN)) return

        bluetoothAdapter?.cancelDiscovery()
    }

    override fun connectToDevice(device: BluetoothDeviceDomain): Flow<IConnectionResult> {
        return flow {
            if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
                throw SecurityException("No BLUETOOTH_CONNECT permission")
            }

            bluetoothSocket = bluetoothAdapter
                ?.getRemoteDevice(device.address)
                ?.createRfcommSocketToServiceRecord(UUID.fromString(SSP_UUID))

            stopDiscovery()

            bluetoothSocket?.let { socket ->
                try {
                    socket.connect()
                    emit(IConnectionResult.ConnectionEstablished)
                } catch (e: IOException) {
                    socket.close()
                    bluetoothSocket = null
                    emit(IConnectionResult.Error("Connection was interrupted"))
                }
            }
        }.onCompletion {
            closeConnection()
        }.flowOn(Dispatchers.IO)
    }

    override fun closeConnection() {
        bluetoothSocket?.close()
        bluetoothSocket = null
    }

    override fun release() {
        context.unregisterReceiver(foundDeviceReceiver)
        closeConnection()
    }

    private fun updatePairedDevices() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) return

        bluetoothAdapter
            ?.bondedDevices
            ?.map { it.toBlueoothDeviceDomain() }
            ?.also { devices -> _pairedDevices.update { devices } }
    }

    private fun hasPermission(permission: String): Boolean =
        context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED

    companion object {
        const val SSP_UUID = "00001101-0000-1000-8000-00805F9B34FB"
    }
}