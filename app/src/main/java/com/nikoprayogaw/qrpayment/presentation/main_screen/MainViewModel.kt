package com.nikoprayogaw.qrpayment.presentation.main_screen

import androidx.lifecycle.ViewModel
import com.nikoprayogaw.qrpayment.presentation.util.AppNavigator
import com.nikoprayogaw.qrpayment.presentation.util.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appNavigator: AppNavigator
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel
}