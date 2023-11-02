package com.nikoprayogaw.qrpayment.common

import android.content.Context
import android.content.Intent
import com.nikoprayogaw.qrpayment.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.*
import java.net.HttpURLConnection

class AuthorizationInterceptor(val context: Context) : Interceptor {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(newRequestWithAccessToken(null, request))

        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            response.close()
            context.startActivity(
                Intent(
                    context,
                    MainActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .putExtra("no_token", true)
            )
        }

        return response
    }

    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
        if (accessToken != "") {
            request.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .addHeader("Accept", "application/json")
                .build()
        } else {
            request.newBuilder()
                .addHeader("Accept", "application/json")
                .build()
        }
}