package ecb.manifest.kowalski.obd_scan.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LifecycleOwner
import java.io.IOException

class BluetoothHelper(private val activity: ComponentActivity,
                      private val lifecycleOwner: LifecycleOwner) {
    private var bluetoothPermission: Boolean = false

    // TODO
    fun scanBluetooth(view: View) {
        val bluetoothManager: BluetoothManager = activity.getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter

        if (bluetoothAdapter == null) {
            Toast.makeText(activity, "Device doesn't support Bluetooth", Toast.LENGTH_LONG)
                .show()
        } else { requestBluetoothPermissions() }
    }

    private val bluetoothPermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            bluetoothPermission = true
            if (isBluetoothEnabled()) { bluetoothScan() }
            else { requestEnableBluetooth() }
        } else { bluetoothPermission = false }
    }

    private val bluetoothActivityResultLauncher = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) { bluetoothScan() }
    }

    fun connectToDevice(device: BluetoothDevice): BluetoothSocket? {
        var bluetoothSocket: BluetoothSocket? = null

        if (ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.BLUETOOTH_CONNECT
        ) != PackageManager.PERMISSION_GRANTED) {
            try {
                bluetoothSocket = device.createRfcommSocketToServiceRecord(device.uuids[0].uuid)
            } catch (e: IOException) {
                return null
            }
        }
        return bluetoothSocket
    }

    fun getPairedDevices(): MutableSet<BluetoothDevice>? {
        val bluetoothManager: BluetoothManager =
            activity.getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter

        val _pairedDevices = if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {} else {}

        return bluetoothAdapter?.bondedDevices
    }

    private fun isBluetoothEnabled(): Boolean {
        val bluetoothManager: BluetoothManager =
            activity.getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter
        return bluetoothAdapter?.isEnabled == true
    }

    private fun requestEnableBluetooth() {
        val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        bluetoothActivityResultLauncher.launch(enableBluetoothIntent)
    }

    private fun requestBluetoothPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            bluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
        } else {
            bluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH_ADMIN)
        }
    }

    private fun bluetoothScan() {
        Toast.makeText(activity, "Bluetooth connected successfully", Toast.LENGTH_LONG)
            .show()
    }
}