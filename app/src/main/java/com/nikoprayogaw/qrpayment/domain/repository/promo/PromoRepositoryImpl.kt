package com.nikoprayogaw.qrpayment.domain.repository.promo

import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import com.nikoprayogaw.qrpayment.data.remote.PromoApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PromoRepositoryImpl @Inject constructor(
    private val promoApi: PromoApi
) : PromoRepository {
    override fun getPromo(): Flow<PromoResponse> = flow {
        emit(promoApi.getPromo())
    }
}