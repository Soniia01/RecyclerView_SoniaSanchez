package com.example.recyclerview_soniasanchez.ui.recyclerView

import com.example.recyclerview_soniasanchez.domain.modelo.Pan

data class MainState(
    val panList: List<Pan> = mutableListOf<Pan>(),
    val error: String?=null
)