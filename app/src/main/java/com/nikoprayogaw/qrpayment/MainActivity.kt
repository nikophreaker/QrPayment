package com.nikoprayogaw.qrpayment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.nikoprayogaw.qrpayment.presentation.main_screen.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}


@Preview(
    widthDp = 411,
    heightDp = 891,
    showBackground = true,
    name = "NEXUS_7",
    device = Devices.NEXUS_5
)
@Composable
fun Preview() {
    MainScreen()
}