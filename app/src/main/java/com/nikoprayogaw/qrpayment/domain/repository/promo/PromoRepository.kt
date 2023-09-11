package com.nikoprayogaw.qrpayment.domain.repository.promo

import com.nikoprayogaw.qrpayment.data.remote.*
import com.nikoprayogaw.qrpayment.domain.model.promo.PromoItem
import javax.inject.Inject

class PromoRepository @Inject constructor(){
    private val promoService = RetrofitInstance.RetrofitInstance.promoService

    suspend fun getPromo(): List<PromoItem> {
        return promoService.getPromo()
    }
}