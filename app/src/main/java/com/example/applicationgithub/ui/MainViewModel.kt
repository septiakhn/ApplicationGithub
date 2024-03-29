package com.example.applicationgithub.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationgithub.data.response.GithubResponse
import com.example.applicationgithub.data.response.ItemsItem
import com.example.applicationgithub.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listUser: LiveData<List<ItemsItem>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
    }
    init {
        findUser("Arief")
    }
    fun findUser(query : String){
        _isLoading.value = true
        val client = ApiConfig.getApiService()
        val call = client.getGithub(query)
        call.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>

            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val githubResponse = response.body()
                    if (githubResponse != null) {
                        _listUser.value = githubResponse.items
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                _isLoading.value = false
            }
        })
    }
}
