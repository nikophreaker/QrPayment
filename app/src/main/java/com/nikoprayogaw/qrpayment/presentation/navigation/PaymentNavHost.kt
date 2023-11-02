package com.nikoprayogaw.qrpayment.presentation.navigation

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.*
import androidx.navigation.compose.*
import com.nikoprayogaw.qrpayment.presentation.navigation.model.GeneralScreen
import com.nikoprayogaw.qrpayment.presentation.screens.paymentdetail.PaymentDetailScreen
import com.nikoprayogaw.qrpayment.presentation.screens.payment.SuccessPaymentScreen

@Composable
fun PaymentNavHost(
    lifeCycleOwner: LifecycleOwner,
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
                lifeCycleOwner = lifeCycleOwner,
                navigateToBack = {
                    navController.navigateUp()
                },
                navigateToSuccess = {
                    navController.navigate(GeneralScreen.PaymentSuccess.route)
                    (context as Activity).finish()
                }
            )
        }
        composable(GeneralScreen.PaymentSuccess.route) {
            SuccessPaymentScreen()
        }
    }
}