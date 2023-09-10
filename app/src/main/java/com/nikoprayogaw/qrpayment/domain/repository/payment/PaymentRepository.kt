package com.nikoprayogaw.qrpayment.domain.repository.payment

import androidx.lifecycle.MutableLiveData
import com.nikoprayogaw.qrpayment.domain.model.payment.*
import kotlinx.coroutines.*

class PaymentRepository(private val paymentDao: PaymentDao) {

    val allPayment = MutableLiveData<List<Payment>>()
    val foundPayment = MutableLiveData<Payment>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addPayment(payment: Payment) {
        coroutineScope.launch(Dispatchers.IO) {
            paymentDao.addPayment(payment)
        }
    }

    fun updatePaymentDetails(payment: Payment) {
        coroutineScope.launch(Dispatchers.IO) {
            paymentDao.updatePaymentDetails(payment)
        }
    }

    fun getAllPayment() {
        coroutineScope.launch(Dispatchers.IO) {
            allPayment.postValue(paymentDao.getAllPaymentUser())
        }
    }

    fun deletePayment(payment: Payment) {
        coroutineScope.launch(Dispatchers.IO) {
            paymentDao.deletePayment(payment)
        }
    }

    fun findPaymentByAccountNumber(accountNumber: String) {
        coroutineScope.launch(Dispatchers.IO) {
            foundPayment.postValue(paymentDao.findPaymentByUserId(accountNumber))
        }
    }
}