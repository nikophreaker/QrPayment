package com.nikoprayogaw.qrpayment.presentation.scan_qr_screen

import androidx.lifecycle.ViewModel
import com.nikoprayogaw.qrpayment.presentation.util.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanQrViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun onBackButtonClicked() {
        appNavigator.tryNavigateBack()
    }
}