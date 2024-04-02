package com.example.applicationgithub.ui.detaill


import android.os.Bundle
import android.support.annotation.StringRes
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.applicationgithub.R
import com.example.applicationgithub.data.response.DetailUserResponse
import com.example.applicationgithub.databinding.ActivityDetailBinding
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

        val username = intent.getStringExtra(USERNAME_KEY)
        Log.d("Detail User", USERNAME_KEY.toString())

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
        viewModel.getUser("users")
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
        Glide.with(this@DetailUser)
            .load(listUser.avatarUrl)
            .circleCrop()
            .into(binding.ivAvatar)
        binding.tvName.text = listUser.name
        binding.tvUsername.text = listUser.login
        binding.tvFollower.text = "${listUser.followers} followers"
        binding.tvFollowing.text = "${listUser.following} following"
    }

    private fun setViewPager(users: String) {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = users
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
//        supportActionBar?.elevation = 0f
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
