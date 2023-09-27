package ecb.manifest.kowalski.obd_scan.ui.viewModels.obd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseObdViewModel (

) : ViewModel() {
    fun getObdData(obdData: MutableLiveData<String>, getter: String?) {
        if (true) {
            obdData.value = getter.toString()
        } else {
            obdData.value = "Not connected"
        }
    }
}