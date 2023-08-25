package ecb.manifest.kowalski.obd_scan.ui.obd_pages.engine_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ecb.manifest.kowalski.obd_scan.R
import ecb.manifest.kowalski.obd_scan.bluetooth.BluetoothHelper
import ecb.manifest.kowalski.obd_scan.databinding.FragmentEngineBinding

class EngineFragment : Fragment() {
    private lateinit var viewModel: EngineViewModel
    private lateinit var binding: FragmentEngineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEngineBinding.inflate(layoutInflater)

        // viewModel = ViewModelProvider(this)[EngineViewModel::class.java]

        return binding.root
    }

//    override fun onStart() {
//        super.onStart()
//        viewModel.rpmData.observe(viewLifecycleOwner) { rpmData ->
//            binding.rpmLabel.text = rpmData
//        }
//
//        viewModel.fetchData()
//    }

    override fun onStop() {
        viewModel.rpmData.removeObservers(viewLifecycleOwner)
        super.onStop()
    }
}