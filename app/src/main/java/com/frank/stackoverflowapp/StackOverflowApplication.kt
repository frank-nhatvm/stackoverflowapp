package com.frank.stackoverflowapp

import android.app.Application
import com.frank.stackoverflowapp.di.DaggerAppComponent

open class StackOverflowApplication: Application() {

    open val appComponent = DaggerAppComponent.create()


}