package com.nikoprayogaw.qrpayment.domain.usecase.promo

import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import com.nikoprayogaw.qrpayment.domain.repository.promo.PromoRepository
import com.nikoprayogaw.qrpayment.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetPromoUseCase @Inject constructor(
    private val promoRepository: PromoRepository
) : BaseUseCase<Unit, Flow<PromoResponse>>() {

    override fun execute(params: Unit): Flow<PromoResponse> {
        return promoRepository.getPromo()
    }
}