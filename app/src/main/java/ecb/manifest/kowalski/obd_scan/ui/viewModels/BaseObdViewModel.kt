package ecb.manifest.kowalski.obd_scan.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ecb.manifest.kowalski.obd_scan.bluetooth.IBluetoothController

open class BaseObdViewModel (
    private val bluetoothController: IBluetoothController
) : ViewModel() {
    fun getObdData(obdData: MutableLiveData<String>, getter: String?) {
        if (bluetoothController.isConnected.value) {
            obdData.value = getter.toString()
        } else {
            obdData.value = "Not connected"
        }
    }
}