package com.example.recyclerview_soniasanchez.data

import com.example.recyclerview_soniasanchez.data.modelo.PanJson
import com.example.recyclerview_soniasanchez.domain.modelo.Pan
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import timber.log.Timber
import java.io.InputStream
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Repository(file: InputStream? = null) {
    init {
            if (pan.isEmpty()) {
                val moshi = Moshi.Builder()
                    .add(LocalDateAdapter())
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
                val listOfCardsType: Type = Types.newParameterizedType(
                    MutableList::class.java,
                    PanJson::class.java
                )

                val json = file?.bufferedReader()?.use { it.readText() }
                val ejemplo = json?.let { contenidoFichero ->
                    moshi.adapter<List<PanJson>>(listOfCardsType)
                        .fromJson(contenidoFichero)
                }
                Timber.tag("Repository").d("Contenido del archivo JSON: %s", ejemplo)
                ejemplo?.let { panJson ->  pan.addAll(panJson.map{ it.toPan() }.toList()) }
            }
    }
    class LocalDateAdapter {
        @ToJson
        fun toJson(value: LocalDate): String {
            return FORMATTER.format(value)
        }

        @FromJson
        fun fromJson(value: String): LocalDate {
            return LocalDate.from(FORMATTER.parse(value))
        }

        companion object {
            private val FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        }
    }
    fun getLista():List<Pan>{
        return pan
    }

    fun getPan(id: Int): Pan? {
        return pan[id]
    }
    fun addPan(pan1: Pan) = pan.add(pan1)

    fun updatePan(pan2: Pan) {
        pan2.id?.let {
            pan[it].id
            pan[it].nombre = pan2.nombre
            pan[it].crunchiness = pan2.crunchiness
            pan[it].tipo = pan2.tipo
            pan[it].textura = pan2.textura
            pan[it].rating = pan2.rating
        }
    }
    fun deletePan(pan3: Pan) = pan.remove(pan3)
    fun getLastID(): Int { return pan.size }
    companion object {
        private val pan = mutableListOf<Pan>()
    }
}