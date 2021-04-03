package com.string.me.up.todohiltassignment.todo

import com.string.me.up.todohiltassignment.todo.data.TodoData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoApi {
    @GET("todos")
    suspend fun getTodoData(): Response<List<TodoData>>

    @GET("users/{userId}/todos")
    suspend fun getUserTodoData(@Path("userId") userId: Int): Response<List<TodoData>>
}