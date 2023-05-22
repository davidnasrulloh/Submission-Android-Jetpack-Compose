package com.davidnasrulloh.ptnindo.data

import com.davidnasrulloh.ptnindo.model.Univ
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    fun getAllUnivData(): Flow<List<Univ>>

    fun getUnivDataById(id: Int): Flow<Univ>

    fun getFavoriteUniv(): Flow<List<Univ>>

    fun updateUnivData(id: Int, newState: Boolean): Flow<Boolean>
}