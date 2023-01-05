package com.jc666.appthreadexample

import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * StrictMode 的運作機制為：
 * 1. 偵測異常行為
 * 2. 通知異常
 *
 *
 * A. 偵測異常行為
 * 1. detectDiskReads() 偵測檔案讀取
 * 2. detectDiskWrites() 偵測檔案寫入
 * 3. detectNetwork() 偵測網路請求
 * 4. detectAll() 偵測所有的異常行為
 *
 * B. 通知異常
 * 1. penaltyLog(): 寫入Log
 * 2. penaltyDeath(): 讓App閃退
 * 3. penaltyDialog(): 顯示Dialog
 * 4. penaltyDeathOnNetwork(): 如果是網路請求就讓閃退
 * 5. penaltyFlashScreen(): 螢幕會閃一下
 *
 *
 *
 *
 *
 */

class StrictModeActivity : AppCompatActivity() {
    private val TAG = StrictModeActivity::class.java.simpleName

    private var btn_strict_mode : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.strict_activity_layout)
        btn_strict_mode = findViewById(R.id.btn_strict_mode)

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads() //偵測檔案讀取
                .detectDiskWrites() //偵測檔案寫入
                .detectNetwork() //偵測網路請求
                .penaltyDialog() //違規的反應方式
                .build()
        )

        btn_strict_mode!!.setOnClickListener {
            //直在接主執行緒寫入資料
            saveData()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun saveData() {
        val pref = getSharedPreferences("StrictModeSample", MODE_PRIVATE)
        pref.edit()
            .putString("data", "data")
            .apply()
    }

}