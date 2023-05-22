package com.davidnasrulloh.ptnindo.di

import com.davidnasrulloh.ptnindo.data.UnivRepository

object Injection {
    fun provideUnivRepository(): UnivRepository {
        return UnivRepository.getInstance()
    }
}