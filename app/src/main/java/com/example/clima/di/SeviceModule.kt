package com.example.clima.di

import com.example.clima.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SeviceModule {

    @Provides
    @Singleton
    fun providesRepository(): Repository{
        return Repository()
    }

}