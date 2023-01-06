package com.jc666.appthreadexample.library

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

class MyInitializer : Initializer<MyLibrary> {
    private val TAG = MyInitializer::class.java.simpleName

    override fun create(context: Context): MyLibrary {
        MyLibrary.initLibrary(context)
        Log.d(TAG,"init MyLibrary.initLibrary(context)")
        return MyLibrary
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        //需要先載入MyInitializer2
        return mutableListOf(MyInitializer2::class.java, MyInitializer3::class.java)
    }
}