package com.nikoprayogaw.qrpayment.common.network.response.promo


import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
): Serializable