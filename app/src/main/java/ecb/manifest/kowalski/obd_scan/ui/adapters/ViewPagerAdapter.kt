package ecb.manifest.kowalski.obd_scan.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ecb.manifest.kowalski.obd_scan.ui.obd_pages.engine_page.EngineFragment
import ecb.manifest.kowalski.obd_scan.ui.obd_pages.fuel_page.FuelFragment
import ecb.manifest.kowalski.obd_scan.ui.obd_pages.status_page.StatusFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        // TODO: Proper item counting
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        // TODO: Improve method
        return when (position) {
            0 -> FuelFragment()
            1 -> EngineFragment()
            else -> StatusFragment()
        }
    }
}