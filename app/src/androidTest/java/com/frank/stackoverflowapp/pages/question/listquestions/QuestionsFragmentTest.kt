package com.frank.stackoverflowapp.pages.question.listquestions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.frank.stackoverflowapp.pages.question.questiondetail.models.Question
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito


@MediumTest
@RunWith(AndroidJUnit4::class)
class QuestionsFragmentTest
{

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val listQuestions = MutableLiveData<List<Question>?>()

    lateinit var viewModel: QuestionsViewModel

    @Before
    fun setup(){
        viewModel = Mockito.mock(QuestionsViewModel::class.java)

        Mockito.`when`(viewModel.listQuestions).thenReturn(listQuestions)

      

    }





}