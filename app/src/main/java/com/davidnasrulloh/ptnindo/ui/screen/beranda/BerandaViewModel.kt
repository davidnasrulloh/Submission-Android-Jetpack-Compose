package com.davidnasrulloh.ptnindo.ui.screen.beranda

import androidx.lifecycle.ViewModel
import com.davidnasrulloh.ptnindo.data.UnivRepository
import com.davidnasrulloh.ptnindo.model.Univ
import com.davidnasrulloh.ptnindo.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BerandaViewModel(
    private val repository: UnivRepository
) : ViewModel() {
    private val _uiStateBeranda: MutableStateFlow<UiState<List<Univ>>> = MutableStateFlow(UiState.Loading)
    val uiStateBeranda get() = _uiStateBeranda

    fun getAllUnivData(){
        viewModelScope.launch {
            repository.getAllUnivData()
                .catch {
                    _uiStateBeranda.value = UiState.Error(it.message.toString())
                }
                .collect{ univ ->
                    _uiStateBeranda.value = UiState.Success(univ)
                }
        }
    }

    fun updateUnivData(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateUnivData(id, newState)
    }

}