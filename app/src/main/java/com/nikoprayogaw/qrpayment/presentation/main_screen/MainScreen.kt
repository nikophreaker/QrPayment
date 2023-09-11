package com.nikoprayogaw.qrpayment.presentation.main_screen

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.nikoprayogaw.qrpayment.presentation.chart_screen.ChartScreen
import com.nikoprayogaw.qrpayment.presentation.components.BottomNavigationBar
import com.nikoprayogaw.qrpayment.presentation.home_screen.HomeScreen
import com.nikoprayogaw.qrpayment.presentation.mutasi_screen.MutasiScreen
import com.nikoprayogaw.qrpayment.presentation.scan_qr_screen.ScanQrActivity
import com.nikoprayogaw.qrpayment.presentation.util.Destination
import com.nikoprayogaw.qrpayment.presentation.util.NavHost
import com.nikoprayogaw.qrpayment.presentation.util.NavigationIntent
import com.nikoprayogaw.qrpayment.presentation.util.composable
import com.nikoprayogaw.qrpayment.ui.theme.QrPaymentTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val screens = listOf(
        Destination.HomeScreen.fullRoute,
        Destination.MutasiScreen.fullRoute,
        "scanner",
        Destination.ChartScreen.fullRoute,
        Destination.UserScreen.fullRoute
    )

    NavigationEffects(
        navigationChannel = mainViewModel.navigationChannel,
        navHostController = navController
    )
//    QrPaymentTheme {
        Scaffold(
            bottomBar = { BottomNavigationBar(screens = screens, navController = navController) },
            modifier = Modifier.fillMaxSize(),
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.size(60.dp),
                    onClick = {
                        context.startActivity(
                            Intent(context, ScanQrActivity::class.java)
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCode2,
                        modifier = Modifier.size(40.dp),
                        contentDescription = "Scan QR"
                    )
                }
            }
        ) {
            it.calculateBottomPadding()
            NavHost(
                navController = navController,
                startDestination = Destination.HomeScreen
            ) {
                composable(destination = Destination.HomeScreen) {
                    HomeScreen()
                }
                composable(destination = Destination.MutasiScreen) {
                    MutasiScreen(navController)
                }
                composable(destination = Destination.ChartScreen) {
                    ChartScreen()
                }
                composable(destination = Destination.UserScreen) {
                }
            }
        }
//    }
}



@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}