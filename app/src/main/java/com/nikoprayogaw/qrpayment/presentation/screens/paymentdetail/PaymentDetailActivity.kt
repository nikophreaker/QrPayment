package com.nikoprayogaw.qrpayment.presentation.screens.paymentdetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.nikoprayogaw.qrpayment.presentation.navigation.PaymentNavHost
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPermissionsApi
@AndroidEntryPoint
class PaymentDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold {
                PaymentNavHost(navController = navController, innerPadding = it)
            }
        }
    }
}