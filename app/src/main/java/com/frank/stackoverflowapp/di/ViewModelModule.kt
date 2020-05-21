package com.frank.stackoverflowapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.frank.stackoverflowapp.base.ViewModelFactory
import com.frank.stackoverflowapp.base.ViewModelKey
import com.frank.stackoverflowapp.pages.question.listquestions.QuestionsViewModel
import com.frank.stackoverflowapp.pages.question.questiondetail.QuestionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(QuestionsViewModel::class)
    abstract fun bindQuestionsViewModel(questionsViewModel: QuestionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuestionViewModel::class)
    abstract fun bindQuestionViewModel(questionViewModel: QuestionViewModel): ViewModel

}