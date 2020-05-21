package com.frank.stackoverflowapp.utils

import android.app.Activity
import com.frank.stackoverflowapp.StackOverflowApplication

val Activity.stackOverFlowApplication: StackOverflowApplication
get() = application as StackOverflowApplication