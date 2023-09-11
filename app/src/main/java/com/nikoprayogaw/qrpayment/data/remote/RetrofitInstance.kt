package com.nikoprayogaw.qrpayment.data.remote

import com.nikoprayogaw.qrpayment.domain.model.promo.PromoItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetrofitInstance {
    interface PromoService {
        @GET("promos")
        suspend fun getPromo(): List<PromoItem>
    }
    object RetrofitInstance {
        private const val BASE_URL = "https://content.digi46.id/"

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val promoService: PromoService by lazy {
            retrofit.create(PromoService::class.java)
        }
    }
}