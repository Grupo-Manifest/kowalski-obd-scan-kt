package ecb.manifest.kowalski.obd_scan.ui.presentation.engine_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EnginePage(
    viewModel: EngineViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    viewModel.fetchData()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Engine Page Component Placeholder")
        Text(text = viewModel.rpmData.value.toString())
    }
}