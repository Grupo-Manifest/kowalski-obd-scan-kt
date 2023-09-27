package ecb.manifest.kowalski.obd_scan.ui.viewModels.obd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ecb.manifest.kowalski.obd_scan.data.repository.WebSocketRepository
import javax.inject.Inject

open class BaseObdViewModel @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
) : ViewModel() {
    fun connectToWebSocket(webSocketUrl: String) {
        webSocketRepository.connectWebSocket(webSocketUrl)
    }

    fun getObdData(obdData: MutableLiveData<String>, getter: String?) {
        if (true) {
            obdData.value = getter.toString()
        } else {
            obdData.value = "Not connected"
        }
    }
}