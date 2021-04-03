package com.string.me.up.todohiltassignment.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.string.me.up.todohiltassignment.R
import com.string.me.up.todohiltassignment.todo.State
import com.string.me.up.todohiltassignment.todo.TodoRepository
import com.string.me.up.todohiltassignment.todo.data.TodoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val singleUserTodoList = MutableLiveData<List<TodoData>>()
    val error = MutableLiveData<Int>()
    val isLoading = MutableLiveData<Boolean>()

    fun getSingleUserTodo(userId: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = todoRepository.getUserToDoes(userId)) {
                is State.Success -> {
                    singleUserTodoList.postValue(response.value)
                    isLoading.postValue(false)
                }

                is State.Failure -> {
                    error.postValue(R.string.error_message)
                    isLoading.postValue(false)

                }
                is State.Exception -> {
                    error.postValue(R.string.error_message)
                    isLoading.postValue(false)
                }
            }
        }
    }
}