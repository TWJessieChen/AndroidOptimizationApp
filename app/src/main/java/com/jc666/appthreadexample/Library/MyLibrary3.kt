package com.jc666.appthreadexample.Library

import android.content.Context
import android.util.Log

object MyLibrary3 {
    private val TAG = MyLibrary3::class.java.simpleName

    fun initLibrary(context: Context){
        Log.d(TAG,"init library3")
    }

}