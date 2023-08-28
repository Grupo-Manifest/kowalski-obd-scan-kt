package ecb.manifest.kowalski.obd_scan.ui.presentation.bluetooth_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ecb.manifest.kowalski.obd_scan.ui.presentation.BluetoothUiState

@Composable
fun BluetoothPage(
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        BluetoothDeviceList(
            pairedDevices = state.pairedDevices,
            scannedDevices = state.scannedDevices,
            onClick = {},
            modifier = Modifier.fillMaxWidth().weight(1f),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Button(onClick = onStartScan) {
                Text(text = "Start scanning")
            }

            Button(onClick = onStopScan) {
                Text(text = "Stop scanning")
            }
        }
    }
}