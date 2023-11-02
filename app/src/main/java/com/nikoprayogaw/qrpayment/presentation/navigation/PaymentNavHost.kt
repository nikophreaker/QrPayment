package com.nikoprayogaw.qrpayment.presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.*
import androidx.navigation.compose.*
import com.nikoprayogaw.qrpayment.presentation.navigation.model.GeneralScreen
import com.nikoprayogaw.qrpayment.presentation.paymentdetail.PaymentDetailScreen
import com.nikoprayogaw.qrpayment.presentation.paymentdetail.SuccessPaymentScreen

@Composable
fun PaymentNavHost(
    lifeCycleOwner: LifecycleOwner,
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = GeneralScreen.Home.route,
        modifier = Modifier.padding(innerPadding),
    ) {
        composable(GeneralScreen.DetailPayment.route) {
            PaymentDetailScreen(
                lifeCycleOwner = lifeCycleOwner,
                navigateToBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(GeneralScreen.PaymentSuccess.route) {
            SuccessPaymentScreen()
        }
    }
}