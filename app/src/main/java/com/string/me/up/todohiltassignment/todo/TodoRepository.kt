package com.string.me.up.todohiltassignment.todo

import com.string.me.up.todohiltassignment.todo.data.TodoData
import javax.inject.Inject

interface ApiRepository {
    suspend fun getAllToDoes(): State<List<TodoData>>
    suspend fun getUserToDoes(userId: Int): State<List<TodoData>>
}

class TodoRepository
@Inject constructor(
    private val todoApi: TodoApi
) : ApiRepository {
    override suspend fun getAllToDoes(): State<List<TodoData>> {
        return try {
            val response = todoApi.getTodoData()
            if (response.isSuccessful) State.Success(response.body()!!)
            else State.Failure(response.message())
        } catch (t: Throwable) {
            State.Exception(t)
        }
    }

    override suspend fun getUserToDoes(userId: Int): State<List<TodoData>> {
        return try {
            val response = todoApi.getUserTodoData(userId)
            if (response.isSuccessful) State.Success(response.body()!!)
            else State.Failure(response.message())
        } catch (t: Throwable) {
            State.Exception(t)
        }
    }
}

sealed class State<out T> {
    data class Success<out T>(val value: T) : State<T>()
    data class Failure<out T>(val error: String, val errorInfo: String? = null) : State<T>()
    data class Exception<out T>(val throwable: Throwable) : State<T>()
}