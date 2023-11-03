package com.nikoprayogaw.qrpayment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikoprayogaw.qrpayment.common.network.Resource
import com.nikoprayogaw.qrpayment.common.network.response.porto.PortoResponse
import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import com.nikoprayogaw.qrpayment.domain.usecase.porto.GetPortoUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.promo.GetPromoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PortoViewModel @Inject constructor(
    private val getPortoUseCase: GetPortoUseCase
) : ViewModel() {

    private val _resourcePorto: MutableStateFlow<Resource<PortoResponse>> =
        MutableStateFlow(Resource.Idle)
    val resourcePorto: StateFlow<Resource<PortoResponse>> = _resourcePorto

    fun getPorto() {
        _resourcePorto.value = Resource.Loading
        getPortoUseCase.execute(Unit).onEach { result ->
            _resourcePorto.value = Resource.Success(result)
        }.catch { e ->
            _resourcePorto.value = Resource.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

    fun cleared() {
        _resourcePorto.value = Resource.Idle
    }
}