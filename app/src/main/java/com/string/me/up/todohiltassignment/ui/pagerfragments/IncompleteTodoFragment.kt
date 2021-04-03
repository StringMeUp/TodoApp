package com.string.me.up.todohiltassignment.ui.pagerfragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.string.me.up.todohiltassignment.R
import com.string.me.up.todohiltassignment.adapters.ProgressAdapter
import com.string.me.up.todohiltassignment.databinding.FragmentIncompleteTodosBinding
import com.string.me.up.todohiltassignment.todo.data.TodoData
import com.string.me.up.todohiltassignment.ui.details.DetailsViewModel

class IncompleteTodoFragment : Fragment(R.layout.fragment_incomplete_todos) {

    lateinit var sharedViewModel: DetailsViewModel
    private var incompleteTodoBinding: FragmentIncompleteTodosBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentIncompleteTodosBinding.bind(view).also { incompleteTodoBinding = it }

        sharedViewModel = ViewModelProvider(requireParentFragment())[DetailsViewModel::class.java]

        val incompleteAdapter = ProgressAdapter()
        binding.incompleteTodosRecycler.apply { adapter = incompleteAdapter }

        sharedViewModel.singleUserTodoList.observe(viewLifecycleOwner, { incompleteTodos ->
            incompleteTodos?.let {
                incompleteAdapter.updateProgressList(incompleteTodos.filter {
                    !it.completed
                } as ArrayList<TodoData>)
            }
        })
    }
}