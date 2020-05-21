package com.frank.stackoverflowapp.pages.question.listquestions.models.response

import com.frank.stackoverflowapp.pages.question.questiondetail.models.Question
import com.squareup.moshi.Json

data class QuestionsResponse(
    @Json(name = "items") val listQuestions: List<Question>,
    @Json(name = "has_more") val hasMore: Boolean = false,
    @Json(name = "quota_max") val quoteMax: Int = 0,
    @Json(name = "quota_remaining") val quoteRemaining: Int = 0
)