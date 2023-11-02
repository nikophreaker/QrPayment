package com.nikoprayogaw.qrpayment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikoprayogaw.qrpayment.common.network.Resource
import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import com.nikoprayogaw.qrpayment.domain.usecase.promo.GetPromoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PromoViewModel @Inject constructor(
    private val promoUseCase: GetPromoUseCase
) : ViewModel() {

    private val _resourcePromo: MutableStateFlow<Resource<PromoResponse>> = MutableStateFlow(Resource.Idle)
    val resourcePromo: StateFlow<Resource<PromoResponse>> = _resourcePromo

    fun getPromo() {
        promoUseCase.execute(Unit).onEach { result ->
            _resourcePromo.value = Resource.Success(result)
        }.catch { e ->
            _resourcePromo.value = Resource.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }
}