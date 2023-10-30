package com.example.msmgrouptest.di

import com.example.msmgrouptest.data.repositories.InitialMsmRepository
import com.example.msmgrouptest.domain.repositories.InitializationRepository
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