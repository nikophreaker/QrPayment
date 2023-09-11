package com.nikoprayogaw.qrpayment.presentation.payment_detail_screen

import androidx.lifecycle.*
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.domain.repository.user.UserRepository
import com.nikoprayogaw.qrpayment.presentation.util.AppNavigator
import com.nikoprayogaw.qrpayment.presentation.util.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentDetailViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun onNavigateToHomeButtonClicked() {
        appNavigator.tryNavigateBack(route = Destination.HomeScreen())
    }

    fun onNavigateToSuccessScreen() {
        appNavigator.tryNavigateTo(route = Destination.SuccesPaymentScreen(), isSingleTop = true)
    }

}