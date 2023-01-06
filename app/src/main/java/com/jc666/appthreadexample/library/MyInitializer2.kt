package com.jc666.appthreadexample.library

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

class MyInitializer2 : Initializer<MyLibrary2> {
    private val TAG = MyInitializer2::class.java.simpleName

    override fun create(context: Context): MyLibrary2 {
        MyLibrary2.initLibrary(context)
        Log.d(TAG,"init MyLibrary2.initLibrary(context)")
        return MyLibrary2
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}