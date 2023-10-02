package ecb.manifest.kowalski.obd_scan.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import com.iamageo.tablayout.MagicTabItem
import com.iamageo.tablayout.MagicTabLayout
import dagger.hilt.android.AndroidEntryPoint
import ecb.manifest.kowalski.obd_scan.ui.presentation.bluetooth_page.MainPage
import ecb.manifest.kowalski.obd_scan.ui.presentation.engine_page.EnginePage
import ecb.manifest.kowalski.obd_scan.ui.presentation.fuel_page.FuelPage
import ecb.manifest.kowalski.obd_scan.ui.presentation.status_page.StatusPage
import ecb.manifest.kowalski.obd_scan.ui.theme.PurpleShell

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column {
                MagicTabLayout(tabList = tabs, tabIndicatorColor = PurpleShell)
            }
        }
    }

    private val tabs = listOf(
        MagicTabItem(title = "Main Page") { MainPage() },
        MagicTabItem(title = "Fuel")   { FuelPage()   },
        MagicTabItem(title = "Engine") { EnginePage() },
        MagicTabItem(title = "Status") { StatusPage() },
    )
}