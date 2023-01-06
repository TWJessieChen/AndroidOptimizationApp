package com.jc666.appthreadexample

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jc666.appthreadexample.api.ApiService
import com.jc666.appthreadexample.data.TodosResponse
import com.jc666.appthreadexample.manager.AppClientManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * RetrofitAPIActivity
 *
 * Retrofit & OKHttp 範例實作
 *
 */

class RetrofitAPIActivity : AppCompatActivity() {
    private val TAG = RetrofitAPIActivity::class.java.simpleName

    private var btn_retrofit_api : Button? = null

    private var apiService : ApiService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.retrofit_activity_layout)
        btn_retrofit_api = findViewById(R.id.btn_retrofit_api)

        apiService = AppClientManager.client.create(ApiService::class.java)

        btn_retrofit_api!!.setOnClickListener {
            //直在接主執行緒寫入資料
            lifecycleScope.launch {
                callAPI()
                Log.d(TAG,"btn_retrofit_api All done")
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private suspend fun callAPI() {
        withContext(Dispatchers.IO){
            apiService!!.getTodo(1).enqueue(object : Callback<TodosResponse> {
                override fun onResponse(call: Call<TodosResponse>, response: Response<TodosResponse>) {
                    Log.d(TAG,"response:$response")
                }
                override fun onFailure(call: Call<TodosResponse>, t: Throwable) {
                    Log.d(TAG,"onFailure:$t")
                }
            })
        }
    }

}
