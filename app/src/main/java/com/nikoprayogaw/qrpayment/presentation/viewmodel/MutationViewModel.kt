package com.nikoprayogaw.qrpayment.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nikoprayogaw.qrpayment.common.network.Resource
import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import com.nikoprayogaw.qrpayment.domain.model.payment.*
import com.nikoprayogaw.qrpayment.domain.repository.payment.*
import com.nikoprayogaw.qrpayment.domain.usecase.payment.AddPaymentUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.payment.GetPaymentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MutationViewModel @Inject constructor(
    private val getPaymentUseCase: GetPaymentUseCase,
    private val addPaymentUseCase: AddPaymentUseCase
) : ViewModel() {


    private val _resourceGetPayment: MutableStateFlow<Resource<List<Payment>>> = MutableStateFlow(Resource.Idle)
    val resourceGetPayment: StateFlow<Resource<List<Payment>>> = _resourceGetPayment

    private val _resourceAddPayment: MutableStateFlow<Resource<Unit>> = MutableStateFlow(Resource.Idle)
    val resourceAddPayment: StateFlow<Resource<Unit>> = _resourceAddPayment

//    val paymentList: LiveData<List<Payment>> = paymentRepository.allPayment
//
//    val foundPayment: LiveData<Payment> = paymentRepository.foundPayment

    fun getAllPayment(){
        _resourceGetPayment.value = Resource.Loading
        getPaymentUseCase.execute(Unit).onEach {
            _resourceGetPayment.value = Resource.Success(it)
            Log.i("SuccessData:", it.toString())
        }.catch { e ->
            Log.i("ErrData:", e.message.toString())
            Log.i("ErrData:", "false")
            _resourceGetPayment.value = Resource.Error(e.message.toString())
        }
    }

    fun addPayment(payment: Payment) {
        _resourceAddPayment.value = Resource.Loading
        addPaymentUseCase.execute(payment).onEach {
            _resourceAddPayment.value = Resource.Success(it)
        }.catch { e ->
            _resourceAddPayment.value = Resource.Error(e.message.toString())
        }
    }

//    fun updatePaymentDetails(payment: Payment) {
//        paymentRepository.updatePaymentDetails(payment)
//    }
//
//    fun findPaymentByUserId(userId: String) {
//        paymentRepository.findPaymentByAccountNumber(userId)
//    }
//
//    fun deletePayment(payment: Payment) {
//        paymentRepository.deletePayment(payment)
//        getAllPayment()
//    }
}