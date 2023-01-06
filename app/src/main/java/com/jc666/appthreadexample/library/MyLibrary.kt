package com.jc666.appthreadexample.library

import android.content.Context
import android.util.Log

object MyLibrary {
    private val TAG = MyLibrary::class.java.simpleName

    fun initLibrary(context: Context){
        Log.d(TAG,"init library")
    }

}