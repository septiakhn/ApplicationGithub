package com.example.applicationgithub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.applicationgithub.R
import com.example.applicationgithub.data.response.GithubResponse
import com.example.applicationgithub.data.response.ItemsItem
import com.example.applicationgithub.data.retrofit.ApiConfig
import com.example.applicationgithub.databinding.ActivityMainBinding
import com.example.applicationgithub.ui.detaill.DetailUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var UserAdapter: UserAdapter
    private val viewModel by viewModels<MainViewModel>()

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UserAdapter = UserAdapter()
        binding.rvUserList.adapter = UserAdapter

        val layoutManager = LinearLayoutManager(this)
        binding.rvUserList.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this,  layoutManager.orientation)
        binding.rvUserList.addItemDecoration(itemDecoration)

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.listUser.observe(this) { listUser ->
            Log.d(TAG, listUser.toString())
            setListUserData(listUser)
        }

        UserAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onClick(user: ItemsItem){
                val intent = Intent(this@MainActivity, DetailUser::class.java)
                intent.putExtra(DetailUser.USERNAME_KEY, user.login.toString())
                startActivity(intent)
            }
        })
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    mainViewModel.findUser(searchBar.text .toString())
                    false
                }
        }
    }

    private fun setListUserData (namaUser: List<ItemsItem>){
        val adapter = UserAdapter()
        adapter.submitList(namaUser)
        binding.rvUserList.adapter = adapter
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
