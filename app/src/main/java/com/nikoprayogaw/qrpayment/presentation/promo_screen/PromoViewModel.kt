package com.nikoprayogaw.qrpayment.presentation.promo_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikoprayogaw.qrpayment.domain.model.promo.PromoItem
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.domain.repository.promo.PromoRepository
import com.nikoprayogaw.qrpayment.domain.repository.user.UserRepository
import com.nikoprayogaw.qrpayment.presentation.util.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromoViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val promoRepository: PromoRepository
) : ViewModel() {

    fun onBackButtonClicked() {
        appNavigator.tryNavigateBack()
    }

    private val _promo = MutableLiveData<List<PromoItem>>()
    val promos: LiveData<List<PromoItem>> = _promo

    fun fetchPromo() {
        viewModelScope.launch {
            try {
                val promo = promoRepository.getPromo()
                _promo.value = promo
                Log.i("promoData", promo.toString())
            } catch (e: Exception) {
                Log.i("promoDataE", e.toString())
                // Handle error
            }
        }
    }
}