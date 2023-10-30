package com.example.recyclerview_soniasanchez.domain.usecases

import com.example.recyclerview_soniasanchez.data.Repository
import com.example.recyclerview_soniasanchez.domain.modelo.Pan

class AddPanUseCase{
    operator fun invoke(pan: Pan) =
        Repository().addPan(pan)
}