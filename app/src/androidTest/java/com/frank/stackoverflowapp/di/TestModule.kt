package com.frank.stackoverflowapp.di

import com.frank.stackoverflowapp.base.ViewModelFactory
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
object TestModule {
    val viewModelFactory = Mockito.mock(ViewModelFactory::class.java)
    
    @Provides
    @Singleton
    fun provideViewModelFactory(): ViewModelFactory = viewModelFactory

}