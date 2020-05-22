package com.frank.stackoverflowapp.pages.question.listquestions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.frank.stackoverflowapp.pages.question.listquestions.models.response.QuestionsResponse
import com.frank.stackoverflowapp.pages.question.questiondetail.models.Owner
import com.frank.stackoverflowapp.pages.question.questiondetail.models.Question
import com.frank.stackoverflowapp.pages.question.questiondetail.repositories.QuestionRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class QuestionsViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    private val questionRepository = Mockito.mock(QuestionRepository::class.java)

    lateinit var questionsViewModel: QuestionsViewModel

    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun getListQuestions_returnEmpty() = runBlockingTest {

        questionsViewModel = QuestionsViewModel(questionRepository)

        val tags = listOf<String>("Android", "iOS")
        val owner = Owner("userid", "profileimage", "displayName", "profileLink")
        val question = Question(tags, owner, true, 1, 1, 1, 12345, 12345678, 12345678, "TestTitle")
        val listQuestions = listOf<Question>(question)
        val questionsResponse = QuestionsResponse(emptyList(), false, 0, 0)

        Mockito.`when`(questionRepository.getListQuestions())
            .thenReturn(CompletableDeferred(questionsResponse))

        questionsViewModel.getListQuestions()

        assertThat(questionsViewModel.listQuestions.value?.size, IsEqual(0))
        assertThat(questionsViewModel.listQuestions.value?.isEmpty(),IsEqual(true))
        assertThat(questionsViewModel.dataLoading.value,IsEqual(false))
        assertNotNull(questionsViewModel.isEmptyQuestion)
        questionsViewModel.isEmptyQuestion.observeForever(mock())
        assertThat(questionsViewModel.isEmptyQuestion.value, IsEqual(true))


    }

    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

    @After
    fun finish(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


}