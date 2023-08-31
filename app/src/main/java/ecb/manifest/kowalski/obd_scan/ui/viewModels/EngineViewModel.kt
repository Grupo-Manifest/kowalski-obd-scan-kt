package ecb.manifest.kowalski.obd_scan.ui.viewModels

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ecb.manifest.kowalski.obd_scan.bluetooth.IBluetoothController
import ecb.manifest.kowalski.obd_scan.obd.ObdManager
import javax.inject.Inject

@HiltViewModel
class EngineViewModel @Inject constructor(
    private val bluetoothController: IBluetoothController
) : BaseObdViewModel(bluetoothController) {
    fun fetchData() {
        val obdManager = ObdManager()

        val bluetoothSocket = bluetoothController.bluetoothSocket

        getObdData(coolantTemperatureData, bluetoothSocket?.let {
            obdManager.getCoolantTemperature(it)
        })
        getObdData(rpmData, bluetoothSocket?.let { obdManager.getRpm(it) })
        getObdData(engineThrottleData, bluetoothSocket?.let { obdManager.getEngineThrottle(it) })
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