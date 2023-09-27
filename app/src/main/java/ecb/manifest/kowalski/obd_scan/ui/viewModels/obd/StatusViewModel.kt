package ecb.manifest.kowalski.obd_scan.ui.viewModels.obd

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ecb.manifest.kowalski.obd_scan.obd.ObdManager
import javax.inject.Inject

@HiltViewModel
class StatusViewModel @Inject constructor(

) : BaseObdViewModel() {
    fun fetchData() {
        val obdManager = ObdManager()
    }

    val oxygenSensorData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}