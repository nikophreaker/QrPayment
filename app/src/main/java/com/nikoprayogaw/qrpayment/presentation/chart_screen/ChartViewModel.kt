package com.nikoprayogaw.qrpayment.presentation.chart_screen

import androidx.lifecycle.ViewModel
import com.nikoprayogaw.qrpayment.presentation.util.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun onBackButtonClicked() {
        appNavigator.tryNavigateBack()
    }
}