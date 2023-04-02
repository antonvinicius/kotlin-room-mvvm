package com.antonvinicius.eventlist.util

import android.content.Context
import androidx.appcompat.app.AlertDialog

object Alert {
    fun show(context: Context, title: String, message: String) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { _, _ -> }
            .create()
            .show()
    }
}