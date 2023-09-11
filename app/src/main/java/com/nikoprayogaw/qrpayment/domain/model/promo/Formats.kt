package com.nikoprayogaw.qrpayment.domain.model.promo


import com.google.gson.annotations.SerializedName

data class Formats(
    @SerializedName("large")
    var large: Large?,
    @SerializedName("medium")
    var medium: Medium?,
    @SerializedName("small")
    var small: Small?,
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail?
)