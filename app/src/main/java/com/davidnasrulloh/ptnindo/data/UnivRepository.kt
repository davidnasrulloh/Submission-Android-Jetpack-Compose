package com.davidnasrulloh.ptnindo.data

import com.davidnasrulloh.ptnindo.model.DummyUnivData
import com.davidnasrulloh.ptnindo.model.Univ
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnivRepository @Inject constructor() : LocalSource {

    private val univData = mutableListOf<Univ>()

    init {
        if(univData.isEmpty()){
            univData.addAll(DummyUnivData.dummyUnivData)
        }
    }
    override fun getUnivData() = flow {
        emit(univData)
    }

    override fun getUnivDataById(id: Int): Flow<Univ> {
        return flowOf(univData.first { it.id == id })
    }

    override fun getFavoriteUniv(): Flow<List<Univ>> {
        return flowOf(univData.filter { it.isFavorite })
    }

    override fun updateUnivData(id: Int, new: Boolean): Flow<Boolean> {
        val indexData = univData.indexOfFirst { it.id == id }
        val resultData = if(indexData >= 0){
            val univ = univData[indexData]
            univData[indexData] = univ.copy(isFavorite = new)
            true
         } else {
             false
         }
        return flowOf(resultData)
    }

}