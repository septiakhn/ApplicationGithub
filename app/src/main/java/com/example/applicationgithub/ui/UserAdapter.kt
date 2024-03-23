package com.example.applicationgithub.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationgithub.R
import com.example.applicationgithub.data.response.ItemsItem


class UserAdapter (private val listAdapter: ArrayList<ItemsItem>) : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)  //list item item user?
        val tvUsername: TextView = itemView.findViewById(R.id.tv_username)

        fun bind(item: ItemsItem) {

        }
    }// Set the image view with the URL from the API. Picasso.get().load(item.avatarUrl).into(imgAvatar) // Set the username tvUsername.text = item.login } }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listAdapter[position])
    }

    override fun getItemCount(): Int {
        return listAdapter.size
    }

}