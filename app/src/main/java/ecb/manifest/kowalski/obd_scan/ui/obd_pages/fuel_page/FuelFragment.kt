package ecb.manifest.kowalski.obd_scan.ui.obd_pages.fuel_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ecb.manifest.kowalski.obd_scan.R

class FuelFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fuel, container, false)
    }
}