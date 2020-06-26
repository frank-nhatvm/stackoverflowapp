package com.frank.stackoverflowapp.pages.question.listquestions

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.frank.stackoverflowapp.MainActivity
import com.frank.stackoverflowapp.R
import com.frank.stackoverflowapp.di.TestModule
import com.frank.stackoverflowapp.util.disableProgressBarAnimations
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*


@MediumTest
@RunWith(AndroidJUnit4::class)
class QuestionsFragmentTest {

    //    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)


    lateinit var fakeViewModel: QuestionsViewModel

    lateinit var navController: NavController


    @Before
    fun setup() {

        fakeViewModel = mock(QuestionsViewModel::class.java)

        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.main_nav_graph)

        `when`(TestModule.viewModelFactory.create(QuestionsViewModel::class.java)).thenReturn(
            fakeViewModel
        )
    }

    @Test
    fun onCreateView_ViewModel_Call_getListQuestion() {

        val scenario = launchFragmentInContainer<QuestionsFragment>()

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
            fragment.disableProgressBarAnimations()
        }

        verify(fakeViewModel).getListQuestions()

    }

//    private fun listMatcher(): RecyclerViewMatcher {
//        return RecyclerViewMatcher(R.id.rcvListQuestions)
//    }


}