package com.string.me.up.todohiltassignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.string.me.up.todohiltassignment.R
import com.string.me.up.todohiltassignment.todo.TodoRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}