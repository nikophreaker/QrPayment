package com.nikoprayogaw.qrpayment.common.network.response.porto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class YearPorto(
    @SerializedName("data")
    val `data`: DataXX?,
    @SerializedName("type")
    val type: String?
)