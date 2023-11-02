package com.nikoprayogaw.qrpayment.domain.repository.promo

import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import kotlinx.coroutines.flow.Flow

interface PromoRepository {
    fun getPromo(): Flow<PromoResponse>
}