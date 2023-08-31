package ecb.manifest.kowalski.obd_scan.ui.presentation.fuel_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ecb.manifest.kowalski.obd_scan.ui.viewModels.obd.FuelViewModel

@Composable
fun FuelPage(
    viewModel: FuelViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Fuel Page Component Placeholder")
    }
}