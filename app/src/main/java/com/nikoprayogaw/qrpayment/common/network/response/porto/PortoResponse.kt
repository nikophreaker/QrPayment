package com.nikoprayogaw.qrpayment.common.network.response.porto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PortoResponse(
    @SerializedName("monthPorto")
    val monthPorto: MonthPorto?,
    @SerializedName("yearPorto")
    val yearPorto: YearPorto?
)