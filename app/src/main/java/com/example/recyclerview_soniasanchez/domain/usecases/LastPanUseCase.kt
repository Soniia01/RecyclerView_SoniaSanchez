package com.example.recyclerview_soniasanchez.domain.usecases
import com.example.recyclerview_soniasanchez.data.Repository

class LastPanUseCase {
    operator fun invoke():Int =
        Repository().getLastID()
}