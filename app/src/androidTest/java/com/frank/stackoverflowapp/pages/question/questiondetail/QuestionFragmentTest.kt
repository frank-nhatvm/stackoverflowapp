package com.frank.stackoverflowapp.pages.question.questiondetail

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.frank.stackoverflowapp.MainActivity
import com.frank.stackoverflowapp.R
import com.frank.stackoverflowapp.di.TestModule
import com.frank.stackoverflowapp.util.disableProgressBarAnimations
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class QuestionFragmentTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    lateinit var fakeModel: QuestionViewModel

    lateinit var navController: NavController

    @Test
    fun createView() {
//val fragment = QuestionFragment()
//        activityScenarioRule.scenario.onActivity {
//                activity ->
//            activity.supportFragmentManager.beginTransaction().add(fragment).commit()
//        }


        fakeModel = Mockito.mock(QuestionViewModel::class.java)
        Mockito.`when`(TestModule.viewModelFactory.create(QuestionViewModel::class.java))
            .thenReturn(fakeModel)

        val fragmentArgs = Bundle().apply {
            putInt("question_id", 1)
        }

        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.main_nav_graph)

//        val scenario = launchFragmentInContainer<QuestionFragment>(fragmentArgs)
//        scenario.onFragment { fragment ->
//            Navigation.setViewNavController(fragment.requireView(), navController)
//            fragment.disableProgressBarAnimations()
//        }

        Mockito.verify(fakeModel).getQuestionDetail()

    }

}