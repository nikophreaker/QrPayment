package com.nikoprayogaw.qrpayment.domain.repository.porto

import com.nikoprayogaw.qrpayment.common.network.response.porto.PortoResponse
import kotlinx.coroutines.flow.Flow

interface PortoRepository {
    fun getPorto(): Flow<PortoResponse>
}