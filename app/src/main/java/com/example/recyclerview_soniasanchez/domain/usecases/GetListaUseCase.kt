package com.example.recyclerview_soniasanchez.domain.usecases

import com.example.recyclerview_soniasanchez.data.Repository
import com.example.recyclerview_soniasanchez.domain.modelo.Pan

class GetListaUseCase(private val repo: Repository){
    operator fun invoke():List<Pan> {
        return repo.getLista()
    }
}