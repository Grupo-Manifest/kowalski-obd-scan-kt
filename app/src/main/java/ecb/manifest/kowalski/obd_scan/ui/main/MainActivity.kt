package ecb.manifest.kowalski.obd_scan.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import com.iamageo.tablayout.MagicTabItem
import com.iamageo.tablayout.MagicTabLayout
import dagger.hilt.android.AndroidEntryPoint
import ecb.manifest.kowalski.obd_scan.ui.presentation.bluetooth_page.BluetoothPage
import ecb.manifest.kowalski.obd_scan.ui.presentation.engine_page.EnginePage
import ecb.manifest.kowalski.obd_scan.ui.presentation.fuel_page.FuelPage
import ecb.manifest.kowalski.obd_scan.ui.presentation.status_page.StatusPage

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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