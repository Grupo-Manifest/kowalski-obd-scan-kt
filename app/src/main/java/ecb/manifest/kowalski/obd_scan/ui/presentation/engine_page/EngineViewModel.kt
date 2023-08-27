package ecb.manifest.kowalski.obd_scan.ui.presentation.engine_page

import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ecb.manifest.kowalski.obd_scan.bluetooth.BluetoothHelper
import ecb.manifest.kowalski.obd_scan.obd.ObdManager

class EngineViewModel(private val activity: ComponentActivity) : ViewModel() {
    val rpmData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun fetchData() {
        val bluetoothHelper = BluetoothHelper(activity, activity)
        val obdManager = ObdManager()

        val device = bluetoothHelper.getPairedDevices()?.first()

        val bluetoothSocket = device?.let { bluetoothHelper.connectToDevice(it) }
        if (bluetoothSocket != null) {
            val rpmResult = obdManager.getRpm(bluetoothSocket)
            rpmData.value = rpmResult

            bluetoothSocket.close()
        } else {
            rpmData.value = "Null"
        }
    }
}