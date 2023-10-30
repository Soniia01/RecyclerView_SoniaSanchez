package com.example.recyclerview_soniasanchez.data

import com.example.recyclerview_soniasanchez.data.modelo.PanJson
import com.example.recyclerview_soniasanchez.domain.modelo.Pan

fun PanJson.toPan() : Pan = Pan(id, nombre, cruch, tipo, textura,rating, imagen)