package com.example.recyclerview_soniasanchez.ui.detail

import android.net.Uri
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.recyclerview_soniasanchez.databinding.DetalleMainBinding
import com.example.recyclerview_soniasanchez.domain.modelo.Pan
import com.example.recyclerview_soniasanchez.domain.usecases.AddPanUseCase
import com.example.recyclerview_soniasanchez.domain.usecases.DeletePanUseCase
import com.example.recyclerview_soniasanchez.domain.usecases.GetPanUseCase
import com.example.recyclerview_soniasanchez.domain.usecases.LastPanUseCase
import com.example.recyclerview_soniasanchez.domain.usecases.UpdatePanUseCase
import com.example.recyclerview_soniasanchez.ui.Constantes

class DetallesMain: AppCompatActivity() {

    private lateinit var binding: DetalleMainBinding

    private val viewModel: DetalleViewModel by viewModels {
        DetalleViewModelFactory(
            AddPanUseCase(),
            GetPanUseCase(),
            UpdatePanUseCase(),
            DeletePanUseCase(),
            LastPanUseCase()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DetalleMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        observarViewModel()
        eventos()
    }
    private fun observarViewModel() = viewModel.uiState.observe(this@DetallesMain) { state ->
        state.error?.let { error ->
            Toast.makeText(this@DetallesMain, error, Toast.LENGTH_SHORT).show()
            viewModel.errorMostrado()
        }
        binding.apply {
            if (state.error == null) {
                val pan = viewModel.uiState.value?.pan
                pan!!.id.let { idValue?.setText(it.toString()) }
                nameValue?.setText(pan.nombre.toString())
                when (pan.tipo) {
                    Constantes.Trigo -> radioTrigo.isChecked = true
                    Constantes.Avena -> radioAvena.isChecked = true
                    Constantes.Centeno -> radioCenteno.isChecked = true
                }
                if (pan.crunchiness == null) {
                    crunchValue.progress = 0
                } else {
                    crunchValue.progress = pan.crunchiness!!
                }
                if (pan.rating == null) {
                    ratingValue.numStars = 0
                } else {
                    ratingValue.rating = pan.rating!!.toFloat()
                }
                if (pan.textura == null) {
                    textureValue.text
                } else {
                    textureValue.setText(pan.textura)
                }
                pan.let {imageView.load(Uri.parse(it.imagen))}
            }
        }
    }
    fun eventos() {
        with(binding) {
            add.setOnClickListener {
                var imagen = ""
                viewModel.addPan(
                    Pan(
                        viewModel.getLastPan(),
                        nameValue.text.toString(),
                        crunchValue.progress,
                        radioGroup2.findViewById<RadioButton?>(radioGroup2.checkedRadioButtonId)?.text.toString(),
                        textureValue.text.toString(),
                        ratingValue.rating.toInt(),
                        imagen
                    )
                )
            }
            delete.setOnClickListener {
                viewModel.uiState.value?.let { it1 -> viewModel.deletePan(it1.pan) }
            }
            update.setOnClickListener {
                var imagen = ""
                viewModel.updatePan(
                    Pan(
                        idValue.text.toString().toInt(),
                        nameValue.text.toString(),
                        crunchValue.progress,
                        radioGroup2.findViewById<RadioButton?>(radioGroup2.checkedRadioButtonId)?.text.toString(),
                        textureValue.text.toString(),
                        ratingValue.rating?.toInt(),
                        imagen
                    )
                )
            }
        }
    }
}