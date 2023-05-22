package com.davidnasrulloh.ptnindo.ui.screen.detailuniv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidnasrulloh.ptnindo.data.UnivRepository
import com.davidnasrulloh.ptnindo.model.Univ
import com.davidnasrulloh.ptnindo.ui.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch


class DetailUnivViewModel(
    private val repository: UnivRepository
) : ViewModel() {
    private val _uiStateDetailUniv: MutableStateFlow<UiState<Univ>> = MutableStateFlow(UiState.Loading)
    val uiStateDetailUniv get() = _uiStateDetailUniv

    fun getUnivDataById(id: Int) = viewModelScope.launch {
        repository.getUnivDataById(id)
            .catch {
                _uiStateDetailUniv.value = UiState.Error(it.message.toString())
            }
            .collect{
                _uiStateDetailUniv.value = UiState.Success(it)
            }
    }

    fun updateUnivData(id: Int, new: Boolean) = viewModelScope.launch {
        repository.updateUnivData(id, !new)
            .collect{ isUpdate ->
                if(isUpdate) getUnivDataById(id)
            }
    }


}


//override fun getUnivDataById(id: Int): Flow<Univ> {
//    return flowOf(univData.first { it.id == id })
//}
//
//override fun getFavoriteUniv(): Flow<List<Univ>> {
//    return flowOf(univData.filter { it.isFavorite })
//}
//
//override fun updateUnivData(id: Int, new: Boolean): Flow<Boolean> {
//    val indexData = univData.indexOfFirst { it.id == id }
//    val resultData = if(indexData >= 0){
//        val univ = univData[indexData]
//        univData[indexData] = univ.copy(isFavorite = new)
//        true
//    } else {
//        false
//    }
//    return flowOf(resultData)
//}