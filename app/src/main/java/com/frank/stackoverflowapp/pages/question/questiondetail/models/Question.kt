package com.frank.stackoverflowapp.pages.question.questiondetail.models

import com.squareup.moshi.Json

data class Question(
    val tags: List<String>?,
    val owner: Owner?,
    @Json(name = "is_answered") val isAnswered: Boolean = false,
    @Json(name = "view_count") val viewCount: Int = 0,
    @Json(name = "answer_count") val answerCount: Int = 0,
    val score: Int = 0,
    @Json(name = "question_id") val questionID: Long?,
    @Json(name = "creation_date") val creationDate: Long?,
    @Json(name = "last_activity_date") val lastActivityDate: Long?,
    val title: String?
)

data class Owner(
    @Json(name = "user_id") val userId: String?,
    @Json(name = "profile_image") val profileImage: String?,
    @Json(name = "display_name") val displayName: String?,
    @Json(name = "link") val profileLink: String?
)