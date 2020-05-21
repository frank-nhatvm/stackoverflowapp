package com.frank.stackoverflowapp.pages.question.listquestions.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frank.stackoverflowapp.databinding.AdapterQuestionBinding
import com.frank.stackoverflowapp.pages.question.questiondetail.models.Question

class QuestionsAdapter(val clickItemListener: QuestionItemListener) :
    ListAdapter<Question, QuestionsAdapter.QuestionViewHolder>(QuestionDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = getItem(position)
        holder.bind(question, clickItemListener)
    }

    class QuestionViewHolder private constructor(val binding: AdapterQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): QuestionViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AdapterQuestionBinding.inflate(layoutInflater)
                return QuestionViewHolder(binding)
            }
        }

        fun bind(question: Question, itemClickListener: QuestionItemListener) {
            binding.question = question
            binding.clickItemListener = itemClickListener
            binding.executePendingBindings()
        }
    }

    class QuestionDiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.questionID == newItem.questionID
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }

    class QuestionItemListener(val clickListener: (id: Long?) -> Unit) {
        fun onClick(question: Question) = clickListener(question.questionID)
    }

}