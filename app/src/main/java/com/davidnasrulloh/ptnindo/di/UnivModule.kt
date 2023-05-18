package com.davidnasrulloh.ptnindo.di

import com.davidnasrulloh.ptnindo.data.UnivRepository
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn

@Module
@InstallIn(SingletonComponent::class)
abstract class UnivModule {

    @Binds
    @Singleton
    abstract fun provideUnivRepository(univRepository: UnivRepository): UnivRepository
}