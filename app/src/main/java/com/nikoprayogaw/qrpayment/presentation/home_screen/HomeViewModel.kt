package com.nikoprayogaw.qrpayment.presentation.home_screen

import androidx.lifecycle.*
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.domain.repository.user.UserRepository
import com.nikoprayogaw.qrpayment.presentation.util.AppNavigator
import com.nikoprayogaw.qrpayment.presentation.util.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun onNavigateToHomeButtonClicked() {
        appNavigator.tryNavigateBack(route = Destination.HomeScreen())
    }

    fun onNavigateToMutasiButtonClicked() {
        appNavigator.tryNavigateTo(route = Destination.MutasiScreen(), popUpToRoute = Destination.HomeScreen())
    }

    fun onNavigateToChartButtonClicked() {
        appNavigator.tryNavigateTo(route = Destination.ChartScreen(), popUpToRoute = Destination.HomeScreen())
    }

    fun onNavigateToUserButtonClicked() {
        appNavigator.tryNavigateTo(route = Destination.UserScreen(), popUpToRoute = Destination.HomeScreen())
    }
}