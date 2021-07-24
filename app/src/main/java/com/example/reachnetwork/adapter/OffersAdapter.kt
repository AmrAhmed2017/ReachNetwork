package com.example.reachnetwork.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
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
        return  OffersViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {

        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = position

    class OffersViewHolder(private val binding: OfferItemBinding, private val context: Context): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private lateinit var offer: Offer

        init {
            binding.root.setOnClickListener(this)
        }
        fun bind(item: Offer) {
            this.offer = item
            binding.apply {
                Glide.with(binding.root)
                    .load(item.cover_image)
                    .centerCrop()
                    .placeholder(R.drawable.empty_pic)
                    .error(R.drawable.error_img)
                    .into(binding.offerImg)
            }
        }

        override fun onClick(v: View?) {
            AlertDialog.Builder(context).setMessage(offer.cta_url).create().show()
        }
    }
}