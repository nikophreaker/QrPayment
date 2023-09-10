package com.nikoprayogaw.qrpayment.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nikoprayogaw.qrpayment.presentation.home_screen.HomeViewModel

@Composable
fun BottomNavigationBar(screens: List<String>, navController: NavHostController) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation {
        screens.forEachIndexed { index, screen ->
            BottomNavigationItem(
                selected = screen == backStackEntry.value?.destination?.route,
                enabled = screen != "scanner",
                onClick = {
                    if (screen != "scanner") onNavigateItemButtonClicked(
                        screen,
                        homeViewModel
                    )
                },
                label = { if (screen != "scanner") Text(text = screen) },
                icon = {
                    if (screen != "scanner") Icon(
                        imageVector = getIconForScreen(screen),
                        contentDescription = screen
                    )
                },
                modifier = Modifier.padding(
                    top = 15.dp,
                    start = 5.dp,
                )
            )
        }
    }
}

@Composable
fun getIconForScreen(screen: String): ImageVector {
    return when (screen) {
        "home" -> Icons.Default.Home
        "mutasi" -> Icons.Default.Assignment
        "chart" -> Icons.Default.BarChart
        "user" -> Icons.Default.Person
        else -> Icons.Default.Home
    }
}

fun onNavigateItemButtonClicked(screen: String, homeViewModel: HomeViewModel) {
    return when (screen) {
        "home" -> homeViewModel.onNavigateToHomeButtonClicked()
        "mutasi" -> homeViewModel.onNavigateToMutasiButtonClicked()
        "chart" -> homeViewModel.onNavigateToChartButtonClicked()
        "user" -> homeViewModel.onNavigateToUserButtonClicked()
        else -> homeViewModel.onNavigateToHomeButtonClicked()
    }
}