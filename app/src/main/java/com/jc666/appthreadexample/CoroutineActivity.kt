package com.jc666.appthreadexample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlin.coroutines.cancellation.CancellationException

/**
 * Coroutine 測試使用方法
 */

class CoroutineActivity : AppCompatActivity() {
    private val TAG = CoroutineActivity::class.java.simpleName

    private var btn_coroutine : Button? = null
    private var btn_coroutine2 : Button? = null
    private var btn_coroutine2_cancel : Button? = null
    private var job : Job? = null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coroutine_activity_layout)

        btn_coroutine = findViewById(R.id.btn_coroutine)
        btn_coroutine2 = findViewById(R.id.btn_coroutine2)
        btn_coroutine2_cancel = findViewById(R.id.btn_coroutine2_cancel)

        btn_coroutine!!.setOnClickListener {
            Log.d(TAG,"btn_coroutine")
            lifecycleScope.launch {
                //取得資料
                val data = networkCall()
                //回到UI執行緒
                Log.d(TAG,"networkCall done!!!")
            }
        }

        btn_coroutine2!!.setOnClickListener {
            createJob()
            Log.d(TAG,"btn_coroutine2")
            lifecycleScope.launch {
                job?.join()
                Log.d(TAG,"btn_coroutine2 All done")
            }
        }

        btn_coroutine2_cancel!!.setOnClickListener {
            Log.d(TAG,"btn_coroutine2_cancel")
            lifecycleScope.launch {
                job?.cancelAndJoin()
                Log.d(TAG,"btn_coroutine2_cancel Cancel all done")
            }
        }

//        createJob()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun createJob() {
        job = lifecycleScope.launch {
            Log.d(TAG,"createJob")
            try {
                val data = networkCall()
                Log.d(TAG,"createJob networkCall")
                val job2 = launch {
                    val data2 = networkCall2()
                    Log.d(TAG,"createJob networkCall2")
                }
            } catch (e: CancellationException) {
                Log.d(TAG,"CancellationException Cancel done")
            }
        }
    }

    //模擬網路請求資料
    private suspend fun networkCall(): String {
        val data = withContext(Dispatchers.IO){
            delay(10000)
            "Data1"
        }
        return data
    }

    private suspend fun networkCall2(): String {
        val data = withContext(Dispatchers.IO){
            delay(10000)
            "Data2"
        }
        return data
    }

}