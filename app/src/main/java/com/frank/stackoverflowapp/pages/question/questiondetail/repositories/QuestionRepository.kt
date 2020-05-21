package com.frank.stackoverflowapp.pages.question.questiondetail.repositories

import com.frank.stackoverflowapp.pages.question.listquestions.models.response.QuestionsResponse
import com.frank.stackoverflowapp.pages.question.listquestions.services.QuestionsService
import kotlinx.coroutines.Deferred
import javax.inject.Inject


open class QuestionRepository @Inject constructor(private val questionsService: QuestionsService){

    // https://api.stackexchange.com/2.2/questions?order=desc&sort=activity&site=stackoverflow
     open fun getListQuestions(): Deferred<QuestionsResponse> {
        return questionsService.getListQuestions()
    }


}