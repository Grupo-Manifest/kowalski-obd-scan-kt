package ecb.manifest.kowalski.obd_scan.ui.obd_pages.engine_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ecb.manifest.kowalski.obd_scan.databinding.FragmentEngineBinding

class EngineFragment : Fragment() {
    private lateinit var viewModel: EngineViewModel
    private lateinit var binding: FragmentEngineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEngineBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onStop() {
        viewModel.rpmData.removeObservers(viewLifecycleOwner)
        super.onStop()
    }
}

@Preview
@Composable
fun EnginePage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Engine Page Component Placeholder")
    }
}