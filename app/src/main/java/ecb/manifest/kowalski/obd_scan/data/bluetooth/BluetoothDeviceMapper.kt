package ecb.manifest.kowalski.obd_scan.data.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import ecb.manifest.kowalski.obd_scan.bluetooth.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBlueoothDeviceDomain(): BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address,
    )
}