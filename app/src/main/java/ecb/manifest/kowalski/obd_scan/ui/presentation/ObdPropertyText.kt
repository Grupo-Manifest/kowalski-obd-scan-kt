package ecb.manifest.kowalski.obd_scan.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ecb.manifest.kowalski.obd_scan.ui.values.PurpleShell

@Composable
fun ObdPropertyText(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(25.dp)
            .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
            .background(color = PurpleShell),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = text, color = Color.White)
    }
}

@Composable
@Preview
fun ObdPropertyTextPreview() {
    ObdPropertyText(text = "Test")
}
