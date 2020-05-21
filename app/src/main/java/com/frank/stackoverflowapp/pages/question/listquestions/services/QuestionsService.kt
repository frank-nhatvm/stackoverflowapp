package com.frank.stackoverflowapp.pages.question.listquestions.services

import com.frank.stackoverflowapp.pages.question.listquestions.models.response.QuestionsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionsService {


    @GET("questions/")
    fun getListQuestions(
        @Query("page") page: Int = 1,
        @Query("pagesize") pagesize: Int = 20,
        @Query("order") order: String = "desc",
        @Query("sort") sort: String = "activity",
        @Query("site") site: String = "stackoverflow"
    ): Deferred<QuestionsResponse>
}