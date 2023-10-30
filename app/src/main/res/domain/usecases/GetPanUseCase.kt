package com.example.appnobasica.domain.usecases

import com.example.recyclerview_soniasanchez.data.Repository
import com.example.recyclerview_soniasanchez.domain.modelo.Pan

class GetPanUseCase {
    operator fun invoke(id: Int): Pan? =
        Repository.getPan(id)
}