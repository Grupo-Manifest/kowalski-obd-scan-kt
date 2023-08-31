package ecb.manifest.kowalski.obd_scan.ui.presentation.engine_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ecb.manifest.kowalski.obd_scan.bluetooth.IBluetoothController
import ecb.manifest.kowalski.obd_scan.obd.ObdManager
import javax.inject.Inject

@HiltViewModel
class EngineViewModel @Inject constructor(
    private val bluetoothController: IBluetoothController
) : ViewModel() {
    val rpmData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun fetchData() {
        val obdManager = ObdManager()

        val bluetoothSocket = bluetoothController.bluetoothSocket
        getObdData(rpmData, bluetoothSocket?.let { obdManager.getRpm(it) })
    }

    private fun getObdData(obdData: MutableLiveData<String>, getter: String?) {
        if (bluetoothController.isConnected.value) {
            obdData.value = getter.toString()
        } else {
            obdData.value = "Not connected"
        }
    }
}