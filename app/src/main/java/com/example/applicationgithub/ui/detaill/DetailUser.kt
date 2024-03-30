package com.example.applicationgithub.ui.detaill

import android.os.Build
import android.os.Bundle
import android.support.annotation.StringRes
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.applicationgithub.R
import com.example.applicationgithub.data.response.DetailUserResponse
import com.example.applicationgithub.data.response.ItemsItem
import com.example.applicationgithub.databinding.ActivityDetailBinding
import com.example.applicationgithub.ui.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailUser : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModels<DetailViewModel>()

    companion object {
        const val USERNAME_KEY = "username_key"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username: String = intent.getStringExtra(USERNAME_KEY)?:""
        Log.d("DetailUser", "Received username: $username")

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
        viewModel.getUser(username!!)
        viewModel.listDetail.observe(this) { listUser ->
            listUser?.let {
                showUser(it)
            }
        }
        if (username != null) {
            setViewPager(username)
        }
    }

    private fun showUser(listUser: DetailUserResponse) {
        Glide.with(this)
            .load(listUser.avatarUrl)
            .circleCrop()
            .into(binding.ivAvatar)
        binding.tvName.text = listUser.name
        binding.tvUsername.text = listUser.login
        binding.tvFollower.text = "${listUser.followers} followers"
        binding.tvFollowing.text = "${listUser.following} following"
    }

    private fun setViewPager(username: String) {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
