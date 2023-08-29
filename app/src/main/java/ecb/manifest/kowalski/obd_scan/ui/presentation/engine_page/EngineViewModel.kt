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

        if (bluetoothController.isConnected.value) {
            val rpmResult = bluetoothController.bluetoothSocket?.let { obdManager.getRpm(it) }
            rpmData.value = rpmResult
        } else {
            rpmData.value = "Not connected"
        }
    }
}