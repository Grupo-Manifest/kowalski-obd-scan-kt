package ecb.manifest.kowalski.obd_scan.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ecb.manifest.kowalski.obd_scan.networking.WebSocketListener
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import javax.inject.Inject

class WebSocketRepository @Inject constructor(
    private val webSocketListener: WebSocketListener,
) {
    private val httpClient = OkHttpClient()
    private var webSocket: WebSocket? = null

    private val _receivedMessage = MutableLiveData<String>()
    val receivedMessage: LiveData<String>
        get() = _receivedMessage

    init {
        webSocketListener.setMessageCallback { message ->
            _receivedMessage.postValue(message)
        }
    }

    fun connectWebSocket(webSocketUrl: String) {
        val request = Request.Builder()
            .url(webSocketUrl)
            .build()

        webSocket = webSocket ?: httpClient.newWebSocket(request, webSocketListener)
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    fun disconnectWebSocket() {
        webSocket?.close(DISCONNECT_CODE, "Android Device Disconnected")
        webSocket = null
    }

    companion object {
        private const val DISCONNECT_CODE = 1000
    }
}