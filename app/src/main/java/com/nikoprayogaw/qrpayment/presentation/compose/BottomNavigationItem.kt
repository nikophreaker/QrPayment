package com.nikoprayogaw.qrpayment.presentation.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.nikoprayogaw.qrpayment.presentation.navigation.model.GeneralScreen

data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = GeneralScreen.Home.route
            ),
            BottomNavigationItem(
                label = "Mutation",
                icon = Icons.Filled.Assignment,
                route = GeneralScreen.Mutation.route
            ),
            BottomNavigationItem(
                label = "Chart",
                icon = Icons.Filled.BarChart,
                route = GeneralScreen.Chart.route
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = Icons.Filled.Person,
                route = GeneralScreen.DetailUser.createRoute(1)
            ),
        )
    }
}