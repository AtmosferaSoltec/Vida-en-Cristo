package com.jjmf.vidaencristo.data.module

import com.jjmf.vidaencristo.data.repository.DistritoRepositoryImpl
import com.jjmf.vidaencristo.data.repository.MiembroRepositoryImpl
import com.jjmf.vidaencristo.domain.repository.DistritoRepository
import com.jjmf.vidaencristo.domain.repository.MiembroRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun miembroRepo(impl: MiembroRepositoryImpl): MiembroRepository

    @Binds
    abstract fun distritoRepo(impl: DistritoRepositoryImpl): DistritoRepository
}