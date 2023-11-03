package com.nikoprayogaw.qrpayment.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikoprayogaw.qrpayment.common.network.Resource
import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import com.nikoprayogaw.qrpayment.domain.model.payment.*
import com.nikoprayogaw.qrpayment.domain.repository.payment.*
import com.nikoprayogaw.qrpayment.domain.usecase.payment.AddPaymentUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.payment.GetPaymentByIdUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.payment.GetPaymentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MutationViewModel @Inject constructor(
    private val getPaymentUseCase: GetPaymentUseCase,
    private val getPaymentByIdUseCase: GetPaymentByIdUseCase,
    private val addPaymentUseCase: AddPaymentUseCase
) : ViewModel() {

    private val _resourceGetPayment: MutableStateFlow<Resource<List<Payment>>> = MutableStateFlow(Resource.Idle)
    val resourceGetPayment: StateFlow<Resource<List<Payment>>> = _resourceGetPayment

    private val _resourceGetPaymentById: MutableStateFlow<Resource<Payment>> = MutableStateFlow(Resource.Idle)
    val resourceGetPaymentById: StateFlow<Resource<Payment>> = _resourceGetPaymentById

    private val _resourceAddPayment: MutableStateFlow<Resource<Unit>> = MutableStateFlow(Resource.Idle)
    val resourceAddPayment: StateFlow<Resource<Unit>> = _resourceAddPayment

//    val paymentList: LiveData<List<Payment>> = paymentRepository.allPayment
//
//    val foundPayment: LiveData<Payment> = paymentRepository.foundPayment

    fun getAllPayment(){
        _resourceGetPayment.value = Resource.Loading
        getPaymentUseCase.execute(Unit).onEach { result ->
            _resourceGetPayment.value = Resource.Success(result)
        }.catch { e ->
            _resourceGetPayment.value = Resource.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

    fun getDetailPayment(params: Int) {
        _resourceGetPaymentById.value = Resource.Loading
        getPaymentByIdUseCase.execute(params).onEach { result ->
            _resourceGetPaymentById.value = Resource.Success(result)
            Log.i("resultPayment :", result.toString())
        }.catch { e ->
            Log.i("resultPaymente :", e.message.toString())
            _resourceGetPaymentById.value = Resource.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

    fun addPayment(payment: Payment) {
        _resourceAddPayment.value = Resource.Loading
        addPaymentUseCase.execute(payment).onEach {
            _resourceAddPayment.value = Resource.Success(it)
        }.catch { e ->
            _resourceAddPayment.value = Resource.Error(e.message.toString())
        }.launchIn(viewModelScope)
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