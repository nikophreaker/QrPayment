package com.nikoprayogaw.qrpayment.common.network.response.promo


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PromoItem(
    @SerializedName("alt")
    var alt: String?,
    @SerializedName("count")
    var count: Int?,
    @SerializedName("created_at")
    var created_At: String?,
    @SerializedName("createdAt")
    var createdAt: String?,
    @SerializedName("desc")
    var desc: String?,
    @SerializedName("desc_promo")
    var descPromo: Any?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("img")
    var img: Img?,
    @SerializedName("latitude")
    var latitude: String?,
    @SerializedName("lokasi")
    var lokasi: String?,
    @SerializedName("longitude")
    var longitude: String?,
    @SerializedName("nama")
    var nama: String?,
    @SerializedName("name_promo")
    var namePromo: Any?,
    @SerializedName("published_at")
    var publishedAt: String?,
    @SerializedName("Title")
    var title: Any?,
    @SerializedName("updated_at")
    var updatedAt: String?
) : Serializable