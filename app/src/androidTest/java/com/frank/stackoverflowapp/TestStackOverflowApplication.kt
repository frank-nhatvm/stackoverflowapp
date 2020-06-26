package com.frank.stackoverflowapp


import com.frank.stackoverflowapp.di.DaggerTestAppComponent
import com.frank.stackoverflowapp.di.TestAppComponent


class TestStackOverflowApplication : StackOverflowApplication() {
    override val appComponent: TestAppComponent = DaggerTestAppComponent.create()
}