package com.example.applicationgithub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicationgithub.data.response.ItemsItem
import com.example.applicationgithub.databinding.ActivityMainBinding
import com.example.applicationgithub.ui.detaill.DetailUser

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
//    private lateinit var userAdapter: UserAdapter
//    private val viewModel by viewModels<MainViewModel>()

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val layoutManager = LinearLayoutManager(this)
        binding.rvUserList.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this,  layoutManager.orientation)
        binding.rvUserList.addItemDecoration(itemDecoration)

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.listUser.observe(this) { listUser ->
            Log.d(TAG, listUser.toString())
            setListUserData(listUser)
        }

//        val mainVm = ViewModelProvider(
//            this, ViewModelProvider.NewInstanceFactory()
//        )[MainViewModel::class.java]
//
////        userAdapter = UserAdapter()
////        binding.rvUserList.adapter = userAdapter
//
//        mainVm.listUser.observe(this) { listUser ->
//            setListUserData(listUser)
//        }
//        binding.rvUserList.setOnClickListener { user ->
//            Log.d("TAG", "Item Diklik")
//            val intent = Intent(this, DetailUser::class.java)
//            intent.putExtra(DetailUser.USERNAME_KEY, "user")
//            startActivity(intent)
//        }
//        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
//            override fun onItemClicked(user: ItemsItem) {
//                Log.d("TAG", "Item Diklik")
//                val intentToDetailUser = Intent(this@MainActivity, DetailUser::class.java)
//                intentToDetailUser.putExtra(DetailUser.USERNAME_KEY, user.login)
//                startActivity(intentToDetailUser)
//            }
//        })
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
//        nanti nyalakan ini kalo search dinyalakan
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    /*Punyamu*/
//                    mainViewModel.findUser(searchBar.text.toString())
                    /*yang benar*/
                    mainViewModel.findUser(searchView.text.toString())
                    false
                }
        }
    }

    private fun setListUserData (namaUser: List<ItemsItem>){
        val adapter = UserAdapter(this)
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

    override fun onItemClick(position: Int) {
        val intentToDetail = Intent(this, DetailUser::class.java)
        val user = (binding.rvUserList.adapter as UserAdapter).currentList[position]
        intentToDetail.putExtra(DetailUser.USERNAME_KEY, user.login)
        startActivity(intentToDetail)
    }

    //ini parameternya panjang kamu setting dimana?? tiaa jangan tidur
//    override fun onItemClick(position: Int) {
//        val intentToDetail = Intent(this, DetailUser::class.java)
//        val user = (binding.rvUserList.adapter as UserAdapter).currentList[position]
//        intentToDetail.putExtra(DetailUser.USERNAME_KEY, user.login)
//        startActivity(intentToDetail)
//    }
}
