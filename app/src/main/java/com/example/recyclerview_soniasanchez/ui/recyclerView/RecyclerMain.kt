package com.example.recyclerview_soniasanchez.ui.recyclerView

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview_soniasanchez.data.Repository
import com.example.recyclerview_soniasanchez.databinding.ActivityMainBinding
import com.example.recyclerview_soniasanchez.domain.modelo.Pan
import com.example.recyclerview_soniasanchez.domain.usecases.GetListaUseCase
import com.example.recyclerview_soniasanchez.ui.PanAdapter
import com.example.recyclerview_soniasanchez.ui.detail.DetallesMain

class RecyclerMain : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PanAdapter

    private val viewModel: RecycleViewModel by viewModels {
        RecycleViewModelFactory(
            GetListaUseCase(Repository( assets.open("pan.json")))
        )
    }
    private fun click(pan:Pan){
        val intent= Intent(this,DetallesMain::class.java)
        intent.putExtra("pan",pan)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            adapter= PanAdapter(GetListaUseCase(Repository( assets.open("pan.json"))).invoke(), ::click)
            RVlistapanes.adapter=adapter
            }
        observarViewModel()
    }
    private fun observarViewModel() = viewModel.uiState.observe(this@RecyclerMain) { state ->
        state.error?.let { error ->
            Toast.makeText(this@RecyclerMain, error, Toast.LENGTH_SHORT).show()
            viewModel.errorMostrado()
        }
        binding.apply {
            if (state.error == null) {
                viewModel.uiState.value?.panList?.let { adapter.cambiarLista(it) }
            }
        }
    }
}