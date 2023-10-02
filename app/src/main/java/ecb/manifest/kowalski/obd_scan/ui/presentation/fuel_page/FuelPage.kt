package ecb.manifest.kowalski.obd_scan.ui.presentation.fuel_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ecb.manifest.kowalski.obd_scan.ui.values.obd_not_connected
import ecb.manifest.kowalski.obd_scan.ui.viewModels.obd.FuelViewModel
import kotlinx.coroutines.delay

@Composable
fun FuelPage(
    viewModel: FuelViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
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

    val fuelLevelValue = viewModel.fuelLevelData.observeAsState().value
    val fuelConsumptionRateValue = viewModel.fuelConsumptionRateData.observeAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val fuelLevel = fuelLevelValue ?: obd_not_connected
        val fuelConsumptionRate = fuelConsumptionRateValue ?: obd_not_connected

        Text(text = "Fuel Page Component Placeholder")
        Text(text = "Fuel Level: $fuelLevel")
        Text(text = "Fuel Consumption Rate: $fuelConsumptionRate")
    }
}