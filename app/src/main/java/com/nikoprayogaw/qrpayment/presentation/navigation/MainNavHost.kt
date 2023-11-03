package com.nikoprayogaw.qrpayment.presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.compose.*
import com.nikoprayogaw.qrpayment.presentation.navigation.model.GeneralScreen
import com.nikoprayogaw.qrpayment.presentation.screens.chart.ChartScreen
import com.nikoprayogaw.qrpayment.presentation.screens.home.HomeScreen
import com.nikoprayogaw.qrpayment.presentation.screens.mutasi.MutasiDetailScreen
import com.nikoprayogaw.qrpayment.presentation.screens.mutasi.MutationScreen
import com.nikoprayogaw.qrpayment.presentation.screens.user.UserScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = GeneralScreen.Home.route,
        modifier = Modifier.padding(innerPadding),
    ) {
        composable(GeneralScreen.Home.route) {
            HomeScreen()
        }
        composable(GeneralScreen.Mutation.route) {
            MutationScreen(navigateToDetail = {
                navController.navigate(GeneralScreen.DetailPayment.createRoute(it))
            })
        }
        composable(
            GeneralScreen.DetailPayment.route,
            arguments = listOf(navArgument("idPayment") { type = NavType.IntType })
        ) { backStackEntry ->
            val idPayment = backStackEntry.arguments?.getInt("idPayment") ?: -1
            MutasiDetailScreen(
                idPayment= idPayment,
            )
        }
        composable(GeneralScreen.Chart.route) {
            ChartScreen()
        }
        composable(
            GeneralScreen.DetailUser.route,
            arguments = listOf(navArgument("idUser") { type = NavType.IntType })
        ) { backStackEntry ->
            val idUser = backStackEntry.arguments?.getInt("idUser") ?: -1
            UserScreen()
        }
    }
}