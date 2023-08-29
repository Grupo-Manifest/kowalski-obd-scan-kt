package ecb.manifest.kowalski.obd_scan.ui.presentation.engine_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ecb.manifest.kowalski.obd_scan.data.bluetooth.BluetoothController
import ecb.manifest.kowalski.obd_scan.obd.ObdManager

class EngineViewModel(private val bluetoothController: BluetoothController) : ViewModel() {
    val rpmData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun fetchData() {
        val obdManager = ObdManager()

        if (bluetoothController.isConnected.value) {
            val rpmResult = bluetoothController.bluetoothSocket?.let { obdManager.getRpm(it) }
            rpmData.value = rpmResult
        } else {
            rpmData.value = "Not connected"
        }
    }
}