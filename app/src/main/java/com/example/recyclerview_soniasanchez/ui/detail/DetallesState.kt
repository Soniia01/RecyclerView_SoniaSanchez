package com.example.recyclerview_soniasanchez.ui.detail

import com.example.recyclerview_soniasanchez.domain.modelo.Pan

data class DetallesState(
    val pan: Pan = Pan(),
    val error: String? = null
)