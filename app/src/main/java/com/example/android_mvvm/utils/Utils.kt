package com.example.android_mvvm.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object Utils {

    fun customDialog(context: Context?, title: String, message:String,dialogListener:DialogListener) {
        context?.let { context ->
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setCancelable(true)
            builder.setNegativeButton("NO"){dialogInterace:DialogInterface?,i:Int ->
                dialogListener.onNegativeClick()
                dialogInterace?.dismiss()
            }
            builder.setPositiveButton("OK"){dialogInterace:DialogInterface?,i:Int ->
                dialogListener.onPositiveClick()
                dialogInterace?.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

    interface DialogListener{
        fun onPositiveClick()
        fun onNegativeClick()
    }
}

