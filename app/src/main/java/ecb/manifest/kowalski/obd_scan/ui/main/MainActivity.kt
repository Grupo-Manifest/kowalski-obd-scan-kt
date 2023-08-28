package ecb.manifest.kowalski.obd_scan.ui.main

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.LaunchedEffect
import com.iamageo.tablayout.MagicTabItem
import com.iamageo.tablayout.MagicTabLayout
import dagger.hilt.android.AndroidEntryPoint
import ecb.manifest.kowalski.obd_scan.ui.presentation.bluetooth_page.BluetoothPage
import ecb.manifest.kowalski.obd_scan.ui.presentation.engine_page.EnginePage
import ecb.manifest.kowalski.obd_scan.ui.presentation.fuel_page.FuelPage
import ecb.manifest.kowalski.obd_scan.ui.presentation.status_page.StatusPage

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val bluetoothManager by lazy {
        applicationContext.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val enableBluetoothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { }

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val canEnableBluetooth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                permissions[Manifest.permission.BLUETOOTH_CONNECT] == true
            } else true

            if (canEnableBluetooth && !isBluetoothEnabled) {
                enableBluetoothLauncher.launch(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                )
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                )
            )
        }

        setContent {
            Column() {
                MagicTabLayout(tabList = tabs)
            }
        }
    }

    private val tabs = listOf(
        MagicTabItem(title = "Bluetooth") { BluetoothPage() },
        MagicTabItem(title = "Fuel")   { FuelPage()   },
        MagicTabItem(title = "Engine") { EnginePage() },
        MagicTabItem(title = "Status") { StatusPage() },
    )
}