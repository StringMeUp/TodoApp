package com.string.me.up.todohiltassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.string.me.up.todohiltassignment.R
import com.string.me.up.todohiltassignment.databinding.TodoCardBinding
import com.string.me.up.todohiltassignment.todo.data.TodoData

class TodoAdapter(private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var todoList = ArrayList<TodoData>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    class TodoViewHolder(
        private val binding: TodoCardBinding,
        private val itemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todoItem: TodoData) {
            binding.run {
                textViewUserId.text = todoItem.userId.toString()
                textViewTodo.setText(R.string.incomplete_task)
            }
            itemView.setOnClickListener { itemClickListener.onItemClicked(todoItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = DataBindingUtil.inflate<TodoCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.todo_card, parent, false
        )
        return TodoViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int = todoList.size
}

interface OnItemClickListener {
    fun onItemClicked(itemDetails: TodoData)
}