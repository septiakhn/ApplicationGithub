package com.example.applicationgithub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicationgithub.R
import com.example.applicationgithub.data.response.GithubResponse
import com.example.applicationgithub.data.retrofit.ApiConfig
import com.example.applicationgithub.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUserList.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this)
        binding.rvUserList.addItemDecoration(itemDecoration)
        findUser()

    }

    private fun DividerItemDecoration(mainActivity: MainActivity): DividerItemDecoration {
        TODO("Not yet implemented")
    }

    private fun findUser(){
        val client = ApiConfig.getApiService()
        val users = "sidiqpermana"
        val call = client.getGithub(users)
        call.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>

            ) {
                if (response.isSuccessful) {
                    val githubResponse = response.body()
                    if (githubResponse != null) {
                        // Set data pengguna ke tampilan
                        // Misalnya: binding.tvUsername.text = githubResponse.username
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}
