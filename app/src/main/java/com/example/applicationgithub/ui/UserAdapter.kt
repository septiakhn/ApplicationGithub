package com.example.applicationgithub.ui

import android.content.Context
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
import com.example.applicationgithub.ui.detaill.FollowAdapter.Companion.DIFF_CALLBACK


interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class UserAdapter(private val listener: OnItemClickListener) :
    ListAdapter<ItemsItem, UserAdapter.ListViewHolder>(DIFF_CALLBACK) {

//    private var onItemClickCallback: OnItemClickCallback? = null
////    private lateinit var onItemClickCallback: OnItemClickCallback
//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    private lateinit var onItemClickCallback: OnItemClickCallback

    //    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding: ItemUserBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding, parent.context, listener)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
//        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked( ItemsItem[holder.adapterPosition])
        //}
//        interface OnItemClickCallback {
//            fun onItemClicked(user: ItemsItem)
//        }
    }

    class ListViewHolder(
        private val binding: ItemUserBinding,
        private val context: Context,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }

        fun bind(item: ItemsItem) {
            binding.tvUsername.text = item.login
            Glide.with(context)
                .load(item.avatarUrl)
                .into(binding.ivAvatar)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

//class UserAdapter: RecylerView