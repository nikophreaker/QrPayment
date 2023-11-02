package com.nikoprayogaw.qrpayment.common.network.response.promo


import com.google.gson.annotations.SerializedName

data class Img(
    @SerializedName("alternativeText")
    var alternativeText: String?,
    @SerializedName("caption")
    var caption: String?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("ext")
    var ext: String?,
    @SerializedName("formats")
    var formats: Formats?,
    @SerializedName("hash")
    var hash: String?,
    @SerializedName("height")
    var height: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("mime")
    var mime: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("previewUrl")
    var previewUrl: Any?,
    @SerializedName("provider")
    var provider: String?,
    @SerializedName("provider_metadata")
    var providerMetadata: Any?,
    @SerializedName("size")
    var size: Double?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("width")
    var width: Int?
)