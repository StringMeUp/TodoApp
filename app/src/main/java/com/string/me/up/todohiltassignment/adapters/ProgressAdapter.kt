package com.string.me.up.todohiltassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.string.me.up.todohiltassignment.R
import com.string.me.up.todohiltassignment.databinding.TodoCardBinding
import com.string.me.up.todohiltassignment.todo.data.TodoData

class ProgressAdapter() :
    RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder>() {

    private val todoProgressList = ArrayList<TodoData>()

    fun updateProgressList(progressList: ArrayList<TodoData>) {
        todoProgressList.clear()
        todoProgressList.addAll(progressList)
        notifyDataSetChanged()
    }

    class ProgressViewHolder(
        private val binding: TodoCardBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todoItem: TodoData) {
            binding.run {
                textViewUserId.text = todoItem.userId.toString()
                textViewTodo.text = todoItem.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        val binding = DataBindingUtil.inflate<TodoCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.todo_card, parent, false
        )
        return ProgressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
        holder.bind(todoProgressList[position])
    }

    override fun getItemCount(): Int = todoProgressList.size
}