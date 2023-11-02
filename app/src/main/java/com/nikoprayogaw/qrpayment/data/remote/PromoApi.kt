package com.nikoprayogaw.qrpayment.data.remote

import com.nikoprayogaw.qrpayment.common.Constants
import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import retrofit2.http.GET

interface PromoApi {
    @GET(Constants.PROMO_ENDPOINT)
    suspend fun getPromo(): PromoResponse

}