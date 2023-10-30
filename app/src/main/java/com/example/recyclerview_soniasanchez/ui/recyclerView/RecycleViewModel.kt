package com.example.recyclerview_soniasanchez.ui.recyclerView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerview_soniasanchez.domain.usecases.GetListaUseCase

class RecycleViewModel(
private val getListaUseCase: GetListaUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState
    init{
        val panes = getListaUseCase()
        if (panes.isEmpty()){
            _uiState.value = MainState(panList = emptyList(), error = "La lista esta vacía")
        } else {
            _uiState.value = MainState(panList = panes, error = null)
        }
    }
    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }
}
/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class RecycleViewModelFactory(
    private val getLista: GetListaUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecycleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecycleViewModel(
            getLista
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}