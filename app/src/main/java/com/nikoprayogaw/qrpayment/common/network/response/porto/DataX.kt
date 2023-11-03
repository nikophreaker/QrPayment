package com.nikoprayogaw.qrpayment.common.network.response.porto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class DataX(
    @SerializedName("nominal")
    val nominal: Int?,
    @SerializedName("trx_date")
    val trxDate: String?
)