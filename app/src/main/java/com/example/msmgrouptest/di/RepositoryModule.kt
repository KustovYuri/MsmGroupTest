package com.example.msmgrouptest.ui.di

import com.example.msmgrouptest.ui.data.repositories.InitialMsmRepository
import com.example.msmgrouptest.ui.domain.repositories.InitializationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindInitialRepository(
        initialRepository: InitialMsmRepository
    ): InitializationRepository
}