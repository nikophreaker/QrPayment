package com.nikoprayogaw.qrpayment.data.remote

import com.nikoprayogaw.qrpayment.common.Constants
import com.nikoprayogaw.qrpayment.common.network.response.porto.PortoResponse
import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import retrofit2.http.GET

interface PortoApi {
    @GET(Constants.PORTO_ENDPOINT)
    suspend fun getPorto(): PortoResponse

}