package ecb.manifest.kowalski.obd_scan.ui.presentation.bluetooth_page

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun BluetoothPage(
    viewModel: BluetoothPageViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    context: Context = LocalContext.current
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.errorMessage) {
        state.errorMessage?.let { message ->
            Toast.makeText(
                context.applicationContext,
                message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        when {
            state.isConnecting -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            else -> {
                BluetoothDeviceList(
                    pairedDevices = state.pairedDevices,
                    scannedDevices = state.scannedDevices,
                    onClick = viewModel::connectToDevice,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Button(onClick = viewModel::startScan) {
                Text(text = "Start scanning")
            }

            Button(onClick = viewModel::stopScan) {
                Text(text = "Stop scanning")
            }
        }
    }
}