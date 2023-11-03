package com.nikoprayogaw.qrpayment.common.network.response.porto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MonthPorto(
    @SerializedName("data")
    val `data`: List<Data>?,
    @SerializedName("type")
    val type: String?
)