package com.nikoprayogaw.qrpayment.presentation.payment_detail_screen

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.nikoprayogaw.qrpayment.presentation.util.Destination
import com.nikoprayogaw.qrpayment.presentation.util.NavHost
import com.nikoprayogaw.qrpayment.presentation.util.composable
import com.nikoprayogaw.qrpayment.ui.theme.QrPaymentTheme

@ExperimentalPermissionsApi
class PaymentDetailActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QrPaymentTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        NavHost(
                            navController = navController,
                            startDestination = Destination.HomeScreen
                        ) {
                            composable(destination = Destination.HomeScreen) {
                                PaymentDetailScreen(intent, liveCycleOwner = this@PaymentDetailActivity,navController)
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}