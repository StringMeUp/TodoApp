package com.string.me.up.todohiltassignment.helper

import android.app.AlertDialog
import android.content.Context
import com.string.me.up.todohiltassignment.R
import kotlin.system.exitProcess

object Helper {
    const val TIME_OUT = 30000L

    fun displayDialog(message: Int, context: Context) {
        val dialog = AlertDialog.Builder(context)
            .setTitle(R.string.error)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.positive_button_label) { _, _ ->
                exitProcess(0)
            }.create().show()
    }
}