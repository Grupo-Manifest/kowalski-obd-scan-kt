package ecb.manifest.kowalski.obd_scan.ui.viewModels.obd

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ecb.manifest.kowalski.obd_scan.data.repository.WebSocketRepository
import javax.inject.Inject

@HiltViewModel
open class BaseObdViewModel @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
) : ViewModel() {
    fun connectToWebSocket(webSocketUrl: String) {
        Log.d("BaseViewModel", "Attempting to connect to the WebSocket")
        webSocketRepository.connectWebSocket(webSocketUrl)
    }

    fun getObdData(obdData: MutableLiveData<String>, getter: String?) {
        if (true) {
            obdData.value = getter.toString()
        } else {
            obdData.value = "Not connected"
        }
    }

    override fun onCleared() {
        super.onCleared()
        webSocketRepository.disconnectWebSocket()
    }
}