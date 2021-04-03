package com.string.me.up.todohiltassignment.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.string.me.up.todohiltassignment.R
import com.string.me.up.todohiltassignment.adapters.OnItemClickListener
import com.string.me.up.todohiltassignment.adapters.TodoAdapter
import com.string.me.up.todohiltassignment.databinding.FragmentTodoBinding
import com.string.me.up.todohiltassignment.helper.Helper
import com.string.me.up.todohiltassignment.todo.data.TodoData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoFragment : Fragment(), OnItemClickListener {

    lateinit var binding: FragmentTodoBinding
    lateinit var todoViewModel: TodoViewModel
    lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_todo, container, false
        )
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        todoAdapter = TodoAdapter(this@TodoFragment)
        setLifecycle(binding, todoViewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoViewModel.getTodoData()
        binding.todoRecyclerView.apply {
            adapter = todoAdapter
        }
        todoViewModel.todoList.observe(viewLifecycleOwner, { todoList ->
            todoList?.let { todos ->
                todoAdapter.todoList = todos.filter { !it.completed } as ArrayList<TodoData>
            }
        })

        todoViewModel.error.observe(viewLifecycleOwner, { error ->
            error?.let { Helper.displayDialog(it, requireContext()) }
        })
    }

    override fun onItemClicked(itemDetails: TodoData) {
        val action = TodoFragmentDirections.actionTodoFragmentToDetailsFragment(itemDetails.userId)
        findNavController().navigate(action)
    }

    private fun setLifecycle(
        binding: FragmentTodoBinding,
        todoViewModel: TodoViewModel
    ) {
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            viewModel = todoViewModel
        }
    }
}