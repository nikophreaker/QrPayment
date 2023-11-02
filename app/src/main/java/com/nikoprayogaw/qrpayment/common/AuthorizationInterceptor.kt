package com.nikoprayogaw.qrpayment.common

import android.content.Context
import android.content.Intent
import com.nikoprayogaw.qrpayment.MainActivity
import com.nikoprayogaw.qrpayment.helper.ReadJSONFromAssets
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.HttpURLConnection

class AuthorizationInterceptor(val context: Context) : Interceptor {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        if (request.url.toString().contains("promos")) {
            val dataBody = ReadJSONFromAssets(context, "promo.json")
            return Response.Builder()
                .code(200)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .message("Success")
                .body(dataBody.toResponseBody())
                .addHeader("content-type", "application/json")
                .build()
        }

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