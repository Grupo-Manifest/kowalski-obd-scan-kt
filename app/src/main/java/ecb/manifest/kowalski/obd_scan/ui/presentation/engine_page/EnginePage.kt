package ecb.manifest.kowalski.obd_scan.ui.presentation.engine_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ecb.manifest.kowalski.obd_scan.ui.viewModels.obd.EngineViewModel
import kotlinx.coroutines.delay

@Composable
fun EnginePage(
    viewModel: EngineViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    LaunchedEffect(true) {
        viewModel.fetchData()
    }

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.fetchData()
            delay(2500)
        }
    }

    val coolantTemperatureValue = viewModel.coolantTemperatureData.observeAsState().value
    val rpmValue = viewModel.rpmData.observeAsState().value
    val engineThrottleValue = viewModel.engineThrottleData.observeAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Engine Page Component Placeholder")

        Text(text = "Engine Coolant Temperature: $coolantTemperatureValue")
        Text(text = "Engine RPM: $rpmValue")
        Text(text = "Engine Throttle Position: $engineThrottleValue")
    }
}