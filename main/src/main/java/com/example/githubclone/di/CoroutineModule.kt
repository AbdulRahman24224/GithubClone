package com.example.githubclone.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Module
@InstallIn(ViewModelComponent::class)
class CoroutinesModule {

    @Provides
    fun provideMainCoroutineScope(): CoroutineScope = CoroutineScope(Job() + Dispatchers.Main.immediate)
}