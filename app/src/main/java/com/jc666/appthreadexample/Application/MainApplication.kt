package com.jc666.appthreadexample.Application

import android.app.Activity
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.ContentResolver
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.jc666.appthreadexample.Library.MyLibrary
import kotlinx.coroutines.*
import java.io.File


/**
 *
 * Application()初始建置
 *
 * @author JC666
 */

class MainApplication  : Application() {
    private val TAG = MainApplication::class.java.simpleName

    val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        // Obtain the FirebaseAnalytics instance.

        registerOnActivity(this)

        appContentResolver = contentResolver

        appContext = applicationContext

        //初始化MyLibrary
//        MyLibrary.initLibrary(this)


    }

    override fun onLowMemory() {
        super.onLowMemory()
        applicationScope.cancel()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == TRIM_MEMORY_UI_HIDDEN) { // Works for Activity
            // Get called every-time when application went to background.
            Log.d(TAG, "application background!!!")
            recordLeaveTime = System.currentTimeMillis()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //通过全局的上下文参数更改相关资源配置
        Log.d(TAG, "onConfigurationChanged")
    }

    class registerOnActivity(val application: Application) {
        private val callback = object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                //Log.d(TAG, " ActivityLifecycleCallbacks -> onActivityCreated")
            }
            override fun onActivityStarted(activity: Activity) {
                //Log.d(TAG, " ActivityLifecycleCallbacks -> onActivityStarted")
            }
            override fun onActivityResumed(activity: Activity)  {
                //Log.d(TAG, " ActivityLifecycleCallbacks -> onActivityResumed")
            }
            override fun onActivityPaused(activity: Activity)  {
                //Log.d(TAG, " ActivityLifecycleCallbacks -> onActivityPaused : " + activity)
            }
            override fun onActivityStopped(activity: Activity)  {
                //Log.d(TAG, " ActivityLifecycleCallbacks -> onActivityStopped")
            }
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle)  {
                //Log.d(TAG, " ActivityLifecycleCallbacks -> onActivitySaveInstanceState")
            }
            override fun onActivityDestroyed(activity: Activity)  {
                //Log.d(TAG, " ActivityLifecycleCallbacks -> onActivityDestroyed")
            }
        }

        fun register() {
            application.registerActivityLifecycleCallbacks(callback)
        }

        init {
            register()
        }

    }

    companion object {
        private val TAG = MainApplication::class.java.simpleName

        var appContentResolver: ContentResolver? = null
            private set
        var appContext: Context? = null

        private var recordLeaveTime: Long = 0L


    }

}