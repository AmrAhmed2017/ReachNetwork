package com.example.reachnetwork.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reachnetwork.R
import com.example.reachnetwork.databinding.OfferItemBinding
import com.example.reachnetwork.model.Offer

class OffersAdapter(val data: List<Offer>): RecyclerView.Adapter<OffersAdapter.OffersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        val binding: OfferItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.offer_item, parent, false)
        return  OffersViewHolder(binding);
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {

        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = position

    class OffersViewHolder(val binding: OfferItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Offer) {
            binding.apply {
                Glide.with(binding.root)
                    .load(item.cover_image)
                    .centerCrop()
                    .placeholder(R.drawable.empty_pic)
                    .error(R.drawable.error_img)
                    .into(binding.offerImg)
            }
        }

    }
}