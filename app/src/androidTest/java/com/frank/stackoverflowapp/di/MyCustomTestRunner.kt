package com.frank.stackoverflowapp.di

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.frank.stackoverflowapp.TestStackOverflowApplication

class MyCustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestStackOverflowApplication::class.java.name, context)
    }

}