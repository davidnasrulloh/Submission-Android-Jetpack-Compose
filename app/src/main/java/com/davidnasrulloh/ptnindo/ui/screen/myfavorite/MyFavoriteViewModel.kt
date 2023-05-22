package com.davidnasrulloh.ptnindo.ui.screen.myfavorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidnasrulloh.ptnindo.data.UnivRepository
import com.davidnasrulloh.ptnindo.model.Univ
import com.davidnasrulloh.ptnindo.ui.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MyFavoriteViewModel(
    private val repository: UnivRepository
) : ViewModel() {
    private val _uiStateFavorite: MutableStateFlow<UiState<List<Univ>>> = MutableStateFlow(UiState.Loading)
    val uiStateFavorite get() = _uiStateFavorite

    fun getFavoriteUniv() = viewModelScope.launch {
        repository.getFavoriteUniv()
            .catch {
                _uiStateFavorite.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiStateFavorite.value = UiState.Success(it)
            }
    }

    fun updateUnivData(id: Int, newState: Boolean){
        repository.updateUnivData(id, newState)
        getFavoriteUniv()
    }

}