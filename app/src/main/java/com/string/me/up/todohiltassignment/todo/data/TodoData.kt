package com.string.me.up.todohiltassignment.todo.data

import com.google.gson.annotations.SerializedName

data class TodoData(
    val userId: Int,
    @SerializedName("id")
    val todoId: Int,
    val title: String,
    val completed: Boolean
)