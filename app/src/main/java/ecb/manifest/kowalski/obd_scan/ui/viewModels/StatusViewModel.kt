package ecb.manifest.kowalski.obd_scan.ui.viewModels

import androidx.lifecycle.MutableLiveData
import ecb.manifest.kowalski.obd_scan.bluetooth.IBluetoothController
import ecb.manifest.kowalski.obd_scan.obd.ObdManager
import javax.inject.Inject

class StatusViewModel @Inject constructor(
    private val bluetoothController: IBluetoothController
) : BaseObdViewModel(bluetoothController) {
    fun fetchData() {
        val obdManager = ObdManager()

        val bluetoothSocket = bluetoothController.bluetoothSocket

        getObdData(oxygenSensorData, bluetoothSocket?.let { obdManager.getOxygenSensor(it) })
    }

    val oxygenSensorData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}