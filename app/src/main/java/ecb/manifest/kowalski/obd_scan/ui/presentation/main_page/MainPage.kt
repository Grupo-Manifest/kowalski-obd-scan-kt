package ecb.manifest.kowalski.obd_scan.ui.presentation.main_page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ecb.manifest.kowalski.obd_scan.data.Constants
import ecb.manifest.kowalski.obd_scan.ui.values.PurpleShell
import ecb.manifest.kowalski.obd_scan.ui.viewModels.WebSocketViewModel

@Composable
fun MainPage(
    viewModel: WebSocketViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(PurpleShell),
            onClick = {
            viewModel.connectToWebSocket(Constants.socketServerUrl)
            },
        ) {
            Text(text = "Connect to OBD device")
        }
    }
}