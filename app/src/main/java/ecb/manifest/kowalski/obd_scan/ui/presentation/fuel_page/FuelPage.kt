package ecb.manifest.kowalski.obd_scan.ui.presentation.fuel_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocalGasStation
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ecb.manifest.kowalski.obd_scan.ui.presentation.ObdPropertyText
import ecb.manifest.kowalski.obd_scan.ui.values.PurpleShell
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 25.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Icon(
            Icons.Rounded.LocalGasStation,
            contentDescription = "Fuel",
            modifier = Modifier.size(75.dp),
            tint = PurpleShell,
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val fuelLevel = fuelLevelValue ?: obd_not_connected
            val fuelConsumptionRate = fuelConsumptionRateValue ?: obd_not_connected

            ObdPropertyText(text = "Fuel Level: $fuelLevel")
            ObdPropertyText(text = "Fuel Consumption Rate: $fuelConsumptionRate")
        }
    }
}