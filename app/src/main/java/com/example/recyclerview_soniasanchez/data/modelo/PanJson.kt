package com.example.recyclerview_soniasanchez.data.modelo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PanJson (
    @Json(name = "id")
    val id: Int?,
    @Json(name = "nombre")
    val nombre: String,
    @Json(name = "crunchiness")
    val cruch: Int?,
    @Json(name = "tipo" )
    var tipo: String,
    @Json(name = "textura")
    val textura: String = "",
    @Json(name = "rating")
    val rating: Int?,
    @Json(name="imagen")
    val imagen: String
)
