package ecb.manifest.kowalski.obd_scan.ui.viewModels.obd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ecb.manifest.kowalski.obd_scan.data.repository.WebSocketRepository
import ecb.manifest.kowalski.obd_scan.obd.ObdManager
import javax.inject.Inject

@HiltViewModel
class StatusViewModel @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
) : ViewModel() {
    fun fetchData() {
        val obdManager = ObdManager(webSocketRepository)

        oxygenSensorData.postValue(obdManager.getOxygenSensor())
    }

    val oxygenSensorData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}