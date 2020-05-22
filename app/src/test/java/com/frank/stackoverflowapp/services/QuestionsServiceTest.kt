package com.frank.stackoverflowapp.services

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import okhttp3.mockwebserver.MockResponse
import com.frank.stackoverflowapp.pages.question.listquestions.services.QuestionsService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.*
import org.mockito.ArgumentMatchers

@RunWith(JUnit4::class)
class QuestionsServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: QuestionsService

    private lateinit var mockServer: MockWebServer

    @Before
    fun createService() {
        mockServer = MockWebServer()

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val moshiConverter = MoshiConverterFactory.create(moshi)

        val retrofit = Retrofit.Builder().addConverterFactory(moshiConverter)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(mockServer.url("/"))
            .build()

        service = retrofit.create(QuestionsService::class.java)
    }

    @After
    fun stopService() {
        mockServer.shutdown()
    }

    @Test
    fun getListQuestion() {
        enqueueResponse("questions.json")
        val questionResponse = runBlocking { service.getListQuestions().await() }

        Assert.assertThat(questionResponse.hasMore, IsEqual(true))
        Assert.assertThat(questionResponse.quoteMax, IsEqual(300))
        Assert.assertThat(questionResponse.quoteRemaining, IsEqual(299))

        val listQuestions = questionResponse.listQuestions
        Assert.assertNotNull(listQuestions)

        Assert.assertThat(listQuestions?.size, IsEqual(1))

        val firstQuestion = listQuestions?.get(0)
        Assert.assertNotNull(firstQuestion)
        Assert.assertThat(firstQuestion?.title, IsEqual("Encoding model used by Facebook"))
        Assert.assertThat(firstQuestion?.viewCount, IsEqual(9))
        Assert.assertThat(firstQuestion?.answerCount, IsEqual(0))
        Assert.assertThat(firstQuestion?.score, IsEqual(0))
    }


    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {

        val fullPath = "/$fileName"

        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")

        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )


    }

}