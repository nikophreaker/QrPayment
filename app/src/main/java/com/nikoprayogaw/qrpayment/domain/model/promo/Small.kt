package com.nikoprayogaw.qrpayment.domain.model.promo


import com.google.gson.annotations.SerializedName

data class Small(
    @SerializedName("ext")
    var ext: String?,
    @SerializedName("hash")
    var hash: String?,
    @SerializedName("height")
    var height: Int?,
    @SerializedName("mime")
    var mime: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("path")
    var path: Any?,
    @SerializedName("size")
    var size: Double?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("width")
    var width: Int?
)