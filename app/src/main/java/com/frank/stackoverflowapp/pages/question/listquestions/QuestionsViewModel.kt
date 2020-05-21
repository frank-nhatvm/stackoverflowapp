package com.frank.stackoverflowapp.pages.question.listquestions

import androidx.lifecycle.*
import com.frank.stackoverflowapp.base.Event
import com.frank.stackoverflowapp.pages.question.questiondetail.models.Question
import com.frank.stackoverflowapp.pages.question.questiondetail.repositories.QuestionRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionsViewModel @Inject constructor(private val questionRepository: QuestionRepository) : ViewModel() {

    private var _listQuestions = MutableLiveData<List<Question>>()
    val listQuestions: LiveData<List<Question>>
        get() = _listQuestions

    val isEmptyQuestion: LiveData<Boolean> = Transformations.map(_listQuestions) {
        it.isEmpty()
    }

    private var _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading


    protected var _navigateToQuestionPage = MutableLiveData<Event<String>>()
    val navigateToQuestionPage: LiveData<Event<String>>
        get() = _navigateToQuestionPage

    init {
        _dataLoading.value = false

    }

     fun getListQuestions() {
         viewModelScope.launch {
             _dataLoading.value = true
             val result = questionRepository.getListQuestions().await()
             _listQuestions.value = result.listQuestions
             _dataLoading.value = false
         }
    }

    fun openQuestionDetailPage(questionId: Long?){
        questionId?.let{
            _navigateToQuestionPage.value = Event(it.toString())
        }
    }


}
