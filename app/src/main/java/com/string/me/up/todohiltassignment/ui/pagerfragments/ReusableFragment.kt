package com.string.me.up.todohiltassignment.ui.pagerfragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.string.me.up.todohiltassignment.R
import com.string.me.up.todohiltassignment.adapters.ProgressAdapter
import com.string.me.up.todohiltassignment.databinding.ReusableLayoutBinding
import com.string.me.up.todohiltassignment.todo.data.TodoData
import com.string.me.up.todohiltassignment.ui.details.DetailsViewModel

class ReusableFragment(var nameTag: String) : Fragment(R.layout.reusable_layout) {

    lateinit var sharedViewModel: DetailsViewModel
    private var completedTodoBinding: ReusableLayoutBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ReusableLayoutBinding.bind(view).also { completedTodoBinding = it }

        sharedViewModel = ViewModelProvider(requireParentFragment())[DetailsViewModel::class.java]
        val completedAdapter = ProgressAdapter()
        binding.reusableRecycler.apply { adapter = completedAdapter }

        if (nameTag == getString(R.string.completed_todo_fragment)) {
            sharedViewModel.singleUserTodoList.observe(viewLifecycleOwner, { completedTodos ->
                completedTodos?.let {
                    completedAdapter.updateProgressList(completedTodos.filter {
                        it.completed
                    } as ArrayList<TodoData>)
                }
            })
        } else if (nameTag == getString(R.string.incomplete_todo_fragment)) {
            sharedViewModel.singleUserTodoList.observe(viewLifecycleOwner, { incompleteTodos ->
                incompleteTodos?.let {
                    completedAdapter.updateProgressList(incompleteTodos.filter {
                        !it.completed
                    } as ArrayList<TodoData>)
                }
            })
        }
    }
}