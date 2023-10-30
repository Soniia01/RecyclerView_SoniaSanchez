package com.example.recyclerview_soniasanchez.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerview_soniasanchez.domain.modelo.Pan
import com.example.recyclerview_soniasanchez.domain.usecases.AddPanUseCase
import com.example.recyclerview_soniasanchez.domain.usecases.DeletePanUseCase
import com.example.recyclerview_soniasanchez.domain.usecases.GetPanUseCase
import com.example.recyclerview_soniasanchez.domain.usecases.LastPanUseCase
import com.example.recyclerview_soniasanchez.domain.usecases.UpdatePanUseCase
import com.example.recyclerview_soniasanchez.ui.Constantes

class DetalleViewModel(
    private val addPanUseCase: AddPanUseCase,
    private val getPanUseCase: GetPanUseCase,
    private val updatePanUseCase:UpdatePanUseCase,
    private val deletePanUseCase: DeletePanUseCase,
    private val lastPanUseCase: LastPanUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<DetallesState>()
    val uiState: LiveData<DetallesState> get() = _uiState
    private var index=0;
    init{
        _uiState.value= DetallesState(
            pan =getPanUseCase(index)!!,
            error =null
        )
    }

    fun addPan(pan: Pan) {
        addPanUseCase(pan)
        _uiState.value = _uiState.value?.copy(error = Constantes.ADD)
        getPan()
    }

    fun updatePan(pan: Pan) {
        updatePanUseCase(pan)
        _uiState.value = _uiState.value?.copy(error = Constantes.UPD)
        getPan()
    }
    fun deletePan(pan: Pan){
        deletePanUseCase(pan)
        _uiState.value = _uiState.value?.copy(error = Constantes.DEL)
        if (lastPanUseCase.invoke() == 0) {
            _uiState.value = DetallesState(
                pan = getPanUseCase(index-1)!!,
                error = null
            )
            addPan(_uiState.value!!.pan)
        }
        getPan()
    }

    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }
    private fun getPan() {
        if (getPanUseCase(index) == null) {
            _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
        } else {
            _uiState.value = _uiState.value?.copy(
                pan = getPanUseCase(index)!!
            )
        }
    }
    fun getLastPan():Int{
        _uiState.value = _uiState.value?.copy(error = Constantes.UPD)
        val lastpan=lastPanUseCase.invoke()
        return lastpan+1
    }
}
/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class DetalleViewModelFactory(
    private val addPan: AddPanUseCase,
    private val getPan: GetPanUseCase,
    private val updatePan:UpdatePanUseCase,
    private val deletePan: DeletePanUseCase,
    private val lastPan: LastPanUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetalleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetalleViewModel(
                addPan,
                getPan,
                updatePan,
                deletePan,
                lastPan,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}