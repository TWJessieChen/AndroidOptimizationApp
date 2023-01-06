package com.jc666.appthreadexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import leakcanary.AppWatcher

/**
 * LeakCanary 的運作機制為：
 * 找出 Memory leak
 *
 * debugImplementation 'com.squareup.leakcanary:leakcanary-android:2.9.1'
 *
 * AppWatcher.objectWatcher.watch(myView, "View was detached")
 *
 */

class LeakCanaryActivity : AppCompatActivity() {
    private val TAG = LeakCanaryActivity::class.java.simpleName

    private var btn_leak_canary : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.leak_canary_activity_layout)

        btn_leak_canary = findViewById(R.id.btn_leak_canary)

        //Activity 被一個 Singleton 參考
        Class1.context = this

        btn_leak_canary!!.setOnClickListener {
            goToOtherActivity(CoroutineActivity::class.java)
        }

        /**
         * 針對單一物件進行監看是否有Memory leak
         * */
        AppWatcher.objectWatcher.watch(btn_leak_canary!!, "View was detached")

    }

    override fun onDestroy() {
        super.onDestroy()
    }


    private fun goToOtherActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

}

object Class1 {
    lateinit var context: Context
}