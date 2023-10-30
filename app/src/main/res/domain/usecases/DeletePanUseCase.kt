package com.example.appnobasica.domain.usecases

import com.example.recyclerview_soniasanchez.data.Repository
import com.example.recyclerview_soniasanchez.domain.modelo.Pan

class DeletePanUseCase {
    operator fun invoke(pan: Pan) =
        Repository.deletePan(pan)
}