package com.jc666.appthreadexample.Library

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

class MyInitializer3 : Initializer<MyLibrary3> {
    private val TAG = MyInitializer3::class.java.simpleName

    override fun create(context: Context): MyLibrary3 {
        MyLibrary3.initLibrary(context)
        Log.d(TAG,"init MyLibrary3.initLibrary(context)")
        return MyLibrary3
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}