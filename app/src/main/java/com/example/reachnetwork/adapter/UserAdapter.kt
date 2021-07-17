package com.example.reachnetwork.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reachnetwork.R
import com.example.reachnetwork.databinding.UserItemBinding
import com.example.reachnetwork.model.User

class UserAdapter(val data: List<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: UserItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.user_item, parent, false)
        return  UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = position

    class UserViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: User) {
            binding.apply {
                Glide.with(binding.root)
                    .load(item.profile_picture)
                    .centerCrop()
                    .placeholder(R.drawable.empty_pic)
                    .error(R.drawable.error_img)
                    .into(binding.profilePic)

                Glide.with(binding.root)
                    .load(item.cover_photo)
                    .centerCrop()
                    .placeholder(R.drawable.empty_pic)
                    .error(R.drawable.error_img)
                    .into(binding.coverPhoto)

                binding.userTextView.text = item.name
            }
        }

    }
}