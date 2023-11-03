package com.nikoprayogaw.qrpayment.domain.repository.payment

import androidx.lifecycle.MutableLiveData
import com.nikoprayogaw.qrpayment.domain.model.payment.*
import dagger.Module
import dagger.hilt.InstallIn
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val paymentDao: PaymentDao
) : PaymentRepository {
    override fun addPayment(payment: Payment) = flow {
        emit(paymentDao.addPayment(payment))
    }

    override fun updatePaymentDetails(payment: Payment) = flow {
        emit(paymentDao.updatePaymentDetails(payment))
    }

    override fun findPaymentById(paymentId: Int): Flow<Payment> = flow {
        emit(paymentDao.findPaymentById(paymentId))
    }

    override fun getAllPayment() = flow {
        emit(paymentDao.getAllPaymentUser())
    }

    override fun deletePayment(payment: Payment) = flow {
        emit(paymentDao.addPayment(payment))
    }

    override fun findPaymentByAccountNumber(accountNumber: String) = flow {
        emit(paymentDao.findPaymentByUserId(accountNumber))
    }


}