package com.nikoprayogaw.qrpayment.presentation.mutasi_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nikoprayogaw.qrpayment.domain.model.payment.*
import com.nikoprayogaw.qrpayment.domain.repository.payment.*
import com.nikoprayogaw.qrpayment.presentation.util.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MutasiViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    fun onBackButtonClicked() {
        appNavigator.tryNavigateBack()
    }

    val paymentList: LiveData<List<Payment>> = paymentRepository.allPayment

    val foundPayment: LiveData<Payment> = paymentRepository.foundPayment

    fun getAllPayment(){
        paymentRepository.getAllPayment()
    }

    fun addPayment(payment: Payment) {
        paymentRepository.addPayment(payment)
    }

    fun updatePaymentDetails(payment: Payment) {
        paymentRepository.updatePaymentDetails(payment)
    }

    fun findPaymentByUserId(userId: String) {
        paymentRepository.findPaymentByAccountNumber(userId)
    }

    fun deletePayment(payment: Payment) {
        paymentRepository.deletePayment(payment)
        getAllPayment()
    }
}