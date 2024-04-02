package com.example.applicationgithub.ui.detaill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.applicationgithub.R
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicationgithub.data.response.DetailUserResponse
import com.example.applicationgithub.data.response.ItemsItem
import com.example.applicationgithub.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    private lateinit var viewModel: FollowViewModel
    private var position: Int = 0
    private var username: String? = null

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowViewModel::class.java)
//
//        showRecyclerList()
//
//        viewModel.isLoading.observe(viewLifecycleOwner) {
//            showLoading(it)
//        }
//        arguments?.let {
//            position = it.getInt(ARG_POSITION)
//            username = it.getString(ARG_USERNAME)
//        }
//        if (position == 1){
//            binding.testUsername.text = "Get Follower $username"
//        } else {
//            binding.testUsername.text = "Get Following $username"
//        }
//    }
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowViewModel::class.java)

        showRecyclerList()

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }

        if (position == 1) {
            viewModel.listFollowers.observe(viewLifecycleOwner) {
                setFollowData(it)
            }
            viewModel.getFollowers(username!!)
        } else {
            viewModel.listFollowing.observe(viewLifecycleOwner) {
                setFollowData(it)
            }
            viewModel.getFollowing(username!!)
        }
    }


    private fun showRecyclerList() {
        val layoutInflater = LinearLayoutManager(requireContext())
        binding.rvFollow.layoutManager = layoutInflater
    }

    private fun setFollowData(users: List<ItemsItem>) {
        val adapter = FollowAdapter()
        adapter.submitList(users)
        binding.rvFollow.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}