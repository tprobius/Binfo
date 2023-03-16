package com.tprobius.binformation.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Number(
    @Json(name = "length")
    val length: Int,
    @Json(name = "luhn")
    val luhn: Boolean
)