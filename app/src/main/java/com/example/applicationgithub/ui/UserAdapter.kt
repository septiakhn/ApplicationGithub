package com.example.applicationgithub.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.applicationgithub.R
import com.example.applicationgithub.data.response.ItemsItem
import com.example.applicationgithub.databinding.ItemUserBinding
import com.example.applicationgithub.ui.detaill.DetailUser


class UserAdapter : ListAdapter<ItemsItem, UserAdapter.ListViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: ((ItemsItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (ItemsItem) -> Unit) {
        onItemClickListener = listener
    }
    class ListViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemsItem) {
            binding.tvUsername.text = item.login
            Glide.with(itemView.context)
                .load(item.avatarUrl)
                .into(binding.ivAvatar)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding: ItemUserBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, DetailUser::class.java)
            v.context.startActivity(intent)

        }
    }

    companion object {
        val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}