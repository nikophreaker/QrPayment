package com.nikoprayogaw.qrpayment.common.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nikoprayogaw.qrpayment.BuildConfig

object NetworkConfig {
    fun getBaseUrl(): String = BuildConfig.BASE_URL
    fun getToken(): String = BuildConfig.BEARER_TOKEN

    fun timeout(): Long = 60

    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .create()
}