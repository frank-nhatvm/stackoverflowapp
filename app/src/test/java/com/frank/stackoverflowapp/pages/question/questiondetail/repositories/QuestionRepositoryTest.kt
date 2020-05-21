package com.frank.stackoverflowapp.pages.question.questiondetail.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.frank.stackoverflowapp.pages.question.listquestions.models.response.QuestionsResponse
import com.frank.stackoverflowapp.pages.question.listquestions.services.QuestionsService
import com.frank.stackoverflowapp.pages.question.questiondetail.models.Owner
import com.frank.stackoverflowapp.pages.question.questiondetail.models.Question
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test

import org.mockito.Mockito.`when`
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class QuestionRepositoryTest {

    @Rule
    @JvmField
    val instantRuleExecutor = InstantTaskExecutorRule()

    private val service = Mockito.mock(QuestionsService::class.java)

    lateinit var questionRepository: QuestionRepository


    @Test
    fun getListQuestions() {

        questionRepository = QuestionRepository(service)

        val tags = listOf<String>("Android", "iOS")
        val owner = Owner("userid", "profileimage", "displayName", "profileLink")
        val question = Question(tags, owner, true, 1, 1, 1, 12345, 12345678, 12345678, "TestTitle")
        val listQuestions = listOf<Question>(question)
        val questionsResponse = QuestionsResponse(listQuestions, true, 100, 1)

        `when`(service.getListQuestions()).thenReturn(CompletableDeferred(questionsResponse))

        val result = runBlocking {
            questionRepository.getListQuestions().await()
        }

        Assert.assertNotNull(result)
        Assert.assertThat(result.hasMore,IsEqual(true))

        val listQuestion = result.listQuestions
        Assert.assertNotNull(listQuestion)
        Assert.assertThat(listQuestion.size,IsEqual(1))
    }
}