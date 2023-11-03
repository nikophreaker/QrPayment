package com.nikoprayogaw.qrpayment.common.network.response.porto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class DataXX(
    @SerializedName("month")
    val month: List<Int?>?
)