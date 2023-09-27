package ecb.manifest.kowalski.obd_scan.ui.viewModels.obd

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ecb.manifest.kowalski.obd_scan.data.repository.WebSocketRepository
import ecb.manifest.kowalski.obd_scan.obd.ObdManager
import javax.inject.Inject

@HiltViewModel
class FuelViewModel @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
) : WebSocketViewModel(webSocketRepository) {
    fun fetchData() {
        val obdManager = ObdManager(webSocketRepository)

        fuelLevelData.postValue(obdManager.getFuelLevel())
        fuelConsumptionRateData.postValue(obdManager.getFuelConsumptionRate())
    }

    val fuelLevelData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val fuelConsumptionRateData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}