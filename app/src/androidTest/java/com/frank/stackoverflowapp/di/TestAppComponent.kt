package com.frank.stackoverflowapp.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestModule::class])
interface TestAppComponent : AppComponent