package com.example.recyclerview_soniasanchez.domain.modelo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pan(
    var id:Int?=null,
    var nombre:String?=null,
    var crunchiness:Int?=null,
    var tipo:String?=null,
    var textura:String?=null,
    var rating:Int?=null,
    var imagen: String? = null
    ) : Parcelable


