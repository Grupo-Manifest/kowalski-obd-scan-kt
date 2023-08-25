package ecb.manifest.kowalski.obd_scan.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import ecb.manifest.kowalski.obd_scan.bluetooth.BluetoothHelper
import ecb.manifest.kowalski.obd_scan.databinding.ActivityMainBinding
import ecb.manifest.kowalski.obd_scan.ui.adapters.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    private lateinit var adapter: ViewPagerAdapter

    private lateinit var bluetoothHelper: BluetoothHelper

    // TODO: The View should only interact with BluetoothHelper through the ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = binding.tabLayout
        viewPager2 = binding.viewPager

        adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        bluetoothHelper = BluetoothHelper(this, this)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // TODO: Improve method
                if (tab != null) viewPager2.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) { /* TODO: Implement method */ }
            override fun onTabReselected(tab: TabLayout.Tab?) { /* TODO: Implement method */ }
        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    override fun onStart() {
        super.onStart()
        bluetoothHelper.scanBluetooth(binding.root)
    }
}