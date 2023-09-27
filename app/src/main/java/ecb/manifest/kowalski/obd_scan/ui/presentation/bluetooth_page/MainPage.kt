package ecb.manifest.kowalski.obd_scan.ui.presentation.bluetooth_page

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ecb.manifest.kowalski.obd_scan.data.Constants
import ecb.manifest.kowalski.obd_scan.ui.viewModels.obd.BaseObdViewModel

@Composable
fun MainPage(
    viewModel: BaseObdViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = {
            viewModel.connectToWebSocket(Constants.socketServerUrl)
        }) {
            Text(text = "Connect")
        }
    }
}