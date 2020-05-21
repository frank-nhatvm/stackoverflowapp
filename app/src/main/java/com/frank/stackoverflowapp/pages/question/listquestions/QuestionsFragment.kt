package com.frank.stackoverflowapp.pages.question.listquestions

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.frank.stackoverflowapp.R
import com.frank.stackoverflowapp.StackOverflowApplication
import com.frank.stackoverflowapp.base.EventObserver
import com.frank.stackoverflowapp.base.ViewModelFactory
import com.frank.stackoverflowapp.databinding.FragmentQuestionsBinding
import com.frank.stackoverflowapp.pages.question.listquestions.adapters.QuestionsAdapter
import com.frank.stackoverflowapp.utils.stackOverFlowApplication
import kotlinx.android.synthetic.main.fragment_questions.*
import javax.inject.Inject

class QuestionsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: QuestionsViewModel

    private lateinit var adapter: QuestionsAdapter

    private lateinit var dataBinding: FragmentQuestionsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        stackOverFlowApplication.appComponent.inject(this)
        viewModel = ViewModelProvider(this,viewModelFactory).get(QuestionsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         dataBinding = FragmentQuestionsBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModel = viewModel

        viewModel.getListQuestions()

        initAdapter()

        viewModel.listQuestions.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })

        initRecycleViewQuestions()

        viewModel.navigateToQuestionPage.observe(viewLifecycleOwner,EventObserver{
            navigateToQuestionDetail(it)
        })

        return dataBinding.root
    }

    private fun initAdapter(){
        adapter = QuestionsAdapter(QuestionsAdapter.QuestionItemListener {
            viewModel.openQuestionDetailPage(it)
        })
    }

    private fun initRecycleViewQuestions(){
        activity?.let{
            dataBinding.rcvListQuestions.layoutManager = LinearLayoutManager(it)
            dataBinding.rcvListQuestions.adapter  = adapter
        }
    }

    private fun navigateToQuestionDetail(questionId: String){
        this.findNavController().navigate(QuestionsFragmentDirections.actionQuestionsFragmentToQuestionFragment(questionId))
    }
}
