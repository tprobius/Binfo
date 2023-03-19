package com.tprobius.binformation.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Binfo(
    @Json(name = "bank")
    val bank: Bank?,
    @Json(name = "brand")
    val brand: String?,
    @Json(name = "country")
    val country: Country?,
    @Json(name = "number")
    val number: Number?,
    @Json(name = "prepaid")
    val prepaid: Boolean?,
    @Json(name = "scheme")
    val scheme: String?,
    @Json(name = "type")
    val type: String?
)