package ecb.manifest.kowalski.obd_scan.ui.viewModels.obd

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ecb.manifest.kowalski.obd_scan.data.repository.WebSocketRepository
import javax.inject.Inject

@HiltViewModel
open class WebSocketViewModel @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
) : ViewModel() {
    val receivedMessage: LiveData<String> = webSocketRepository.receivedMessage
    fun connectToWebSocket(webSocketUrl: String) {
        Log.d("BaseViewModel", "Attempting to connect to the WebSocket")
        webSocketRepository.connectWebSocket(webSocketUrl)
    }

    fun getObdData() {
        webSocketRepository.sendMessage("A")
    }

    override fun onCleared() {
        super.onCleared()
        webSocketRepository.disconnectWebSocket()
    }
}