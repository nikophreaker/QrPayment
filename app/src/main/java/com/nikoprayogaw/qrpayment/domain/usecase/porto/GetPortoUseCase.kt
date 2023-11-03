package com.nikoprayogaw.qrpayment.domain.usecase.porto

import com.nikoprayogaw.qrpayment.common.network.response.porto.PortoResponse
import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import com.nikoprayogaw.qrpayment.domain.repository.porto.PortoRepository
import com.nikoprayogaw.qrpayment.domain.repository.promo.PromoRepository
import com.nikoprayogaw.qrpayment.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetPortoUseCase @Inject constructor(
    private val portoRepository: PortoRepository
) : BaseUseCase<Unit, Flow<PortoResponse>>() {

    override fun execute(params: Unit): Flow<PortoResponse> {
        return portoRepository.getPorto()
    }
}