package com.frank.stackoverflowapp.utils

import androidx.fragment.app.Fragment
import com.frank.stackoverflowapp.StackOverflowApplication

val Fragment.stackOverFlowApplication: StackOverflowApplication
get() = requireActivity().stackOverFlowApplication