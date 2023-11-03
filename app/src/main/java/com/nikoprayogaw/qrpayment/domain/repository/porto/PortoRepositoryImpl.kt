package com.nikoprayogaw.qrpayment.domain.repository.porto

import com.nikoprayogaw.qrpayment.common.network.response.porto.PortoResponse
import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import com.nikoprayogaw.qrpayment.data.remote.PortoApi
import com.nikoprayogaw.qrpayment.data.remote.PromoApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PortoRepositoryImpl @Inject constructor(
    private val portoApi: PortoApi
) : PortoRepository {
    override fun getPorto(): Flow<PortoResponse> = flow {
        emit(portoApi.getPorto())
    }
}