package com.frank.stackoverflowapp.di

import com.frank.stackoverflowapp.MainActivity
import com.frank.stackoverflowapp.pages.question.listquestions.QuestionsFragment
import com.frank.stackoverflowapp.pages.question.questiondetail.QuestionFragment
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(questionsFragment: QuestionsFragment)
    fun inject(questionFragment: QuestionFragment)


}