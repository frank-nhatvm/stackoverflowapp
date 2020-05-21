package com.frank.stackoverflowapp.pages.question.questiondetail

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.frank.stackoverflowapp.R
import com.frank.stackoverflowapp.base.ViewModelFactory
import com.frank.stackoverflowapp.utils.stackOverFlowApplication
import javax.inject.Inject

class QuestionFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: QuestionViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        stackOverFlowApplication.appComponent.inject(this)
        viewModel = ViewModelProvider(this,viewModelFactory).get(QuestionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = QuestionFragmentArgs.fromBundle(requireArguments())
        args.questionId


        return inflater.inflate(R.layout.fragment_question, container, false)
    }



}
