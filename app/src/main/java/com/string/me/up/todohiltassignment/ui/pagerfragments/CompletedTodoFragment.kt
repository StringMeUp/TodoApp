package com.string.me.up.todohiltassignment.ui.pagerfragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.string.me.up.todohiltassignment.R
import com.string.me.up.todohiltassignment.adapters.ProgressAdapter
import com.string.me.up.todohiltassignment.databinding.FragmentCompletedTodosBinding
import com.string.me.up.todohiltassignment.todo.data.TodoData
import com.string.me.up.todohiltassignment.ui.details.DetailsViewModel

class CompletedTodoFragment : Fragment(R.layout.fragment_completed_todos) {

    lateinit var sharedViewModel: DetailsViewModel
    private var completedTodoBinding: FragmentCompletedTodosBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCompletedTodosBinding.bind(view).also { completedTodoBinding = it }

        sharedViewModel = ViewModelProvider(requireParentFragment())[DetailsViewModel::class.java]
        val completedAdapter = ProgressAdapter()
        binding.compltetedTodosRecycler.apply { adapter = completedAdapter }

        sharedViewModel.singleUserTodoList.observe(viewLifecycleOwner, { completedTodos ->
            completedTodos?.let {
                completedAdapter.updateProgressList(completedTodos.filter {
                    it.completed
                } as ArrayList<TodoData>)
            }
        })
    }
}