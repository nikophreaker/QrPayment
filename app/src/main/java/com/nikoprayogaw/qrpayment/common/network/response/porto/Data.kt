package com.nikoprayogaw.qrpayment.common.network.response.porto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Data(
    @SerializedName("data")
    val `data`: List<DataX>?,
    @SerializedName("label")
    val label: String?,
    @SerializedName("percentage")
    val percentage: String?
)