package ecb.manifest.kowalski.obd_scan.ui.viewModels.obd

import androidx.lifecycle.MutableLiveData
import ecb.manifest.kowalski.obd_scan.bluetooth.IBluetoothController
import ecb.manifest.kowalski.obd_scan.obd.ObdManager
import javax.inject.Inject

class FuelViewModel @Inject constructor(
    private val bluetoothController: IBluetoothController
) : BaseObdViewModel(bluetoothController) {
    fun fetchData() {
        val obdManager = ObdManager()

        val bluetoothSocket = bluetoothController.bluetoothSocket

        getObdData(fuelLevelData, bluetoothSocket?.let { obdManager.getFuelLevel(it) })
        getObdData(fuelConsumptionRateData, bluetoothSocket?.let {
            obdManager.getFuelConsumptionRate(it)
        })
    }

    val fuelLevelData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val fuelConsumptionRateData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}