package com.nikoprayogaw.qrpayment.presentation.navigation

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.*
import androidx.navigation.compose.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.nikoprayogaw.qrpayment.helper.findActivity
import com.nikoprayogaw.qrpayment.presentation.navigation.model.GeneralScreen
import com.nikoprayogaw.qrpayment.presentation.screens.paymentdetail.PaymentDetailScreen
import com.nikoprayogaw.qrpayment.presentation.screens.payment.SuccessPaymentScreen
import com.nikoprayogaw.qrpayment.presentation.screens.paymentdetail.PaymentDetailActivity

@Composable
fun PaymentNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = GeneralScreen.DetailPayment.route,
        modifier = Modifier.padding(innerPadding),
    ) {
        composable(GeneralScreen.DetailPayment.route) {
            PaymentDetailScreen(
                navigateToSuccess = {
                    navController.navigate(GeneralScreen.PaymentSuccess.route)
                }
            )
        }
        composable(GeneralScreen.PaymentSuccess.route) {
            SuccessPaymentScreen(
                navigateToHome = {
                    (context as? Activity)?.finish()
                }
            )
        }
    }
}