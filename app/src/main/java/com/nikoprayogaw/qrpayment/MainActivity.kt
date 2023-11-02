package com.nikoprayogaw.qrpayment

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.nikoprayogaw.qrpayment.helper.toast
import com.nikoprayogaw.qrpayment.presentation.screens.MainScreen
import com.nikoprayogaw.qrpayment.presentation.screens.scanqr.ScanQrActivity
import com.nikoprayogaw.qrpayment.ui.theme.QrPaymentTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QrPaymentTheme {
                MainScreen(
                    navigateToScanQr = {
                        val intent = Intent(this, ScanQrActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
        val noToken = intent.getBooleanExtra("no_token", false)
        if (noToken) {
            toast(getString(R.string.no_token), true)
        }
    }
}