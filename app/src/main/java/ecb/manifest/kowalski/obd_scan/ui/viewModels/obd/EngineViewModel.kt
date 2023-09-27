package ecb.manifest.kowalski.obd_scan.ui.viewModels.obd

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ecb.manifest.kowalski.obd_scan.data.repository.WebSocketRepository
import ecb.manifest.kowalski.obd_scan.obd.ObdManager
import javax.inject.Inject

@HiltViewModel
class EngineViewModel @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
) : WebSocketViewModel(webSocketRepository) {
    fun fetchData() {
        val obdManager = ObdManager()
    }

    val coolantTemperatureData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val rpmData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val engineThrottleData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}