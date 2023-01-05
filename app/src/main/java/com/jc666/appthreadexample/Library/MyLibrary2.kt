package com.jc666.appthreadexample.Library

import android.content.Context
import android.util.Log

object MyLibrary2 {
    private val TAG = MyLibrary2::class.java.simpleName

    fun initLibrary(context: Context){
        Log.d(TAG,"init library2")
    }

}