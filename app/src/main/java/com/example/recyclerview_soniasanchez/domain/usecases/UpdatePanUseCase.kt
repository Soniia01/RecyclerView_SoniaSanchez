package com.example.recyclerview_soniasanchez.domain.usecases

import com.example.recyclerview_soniasanchez.data.Repository
import com.example.recyclerview_soniasanchez.domain.modelo.Pan

class UpdatePanUseCase{
    operator fun invoke(pan: Pan) =
        Repository().updatePan(pan)
}