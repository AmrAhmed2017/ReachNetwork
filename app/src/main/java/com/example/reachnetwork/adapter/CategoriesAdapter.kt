package com.example.reachnetwork.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reachnetwork.R
import com.example.reachnetwork.databinding.DiscoverItemBinding
import com.example.reachnetwork.model.Category
import com.example.reachnetwork.util.RecyclerItemDecoration

class CategoriesAdapter(val data: List<Category>, private val context: Context): RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private lateinit var binding: DiscoverItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.discover_item, parent, false)
        return  CategoriesViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {

        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = position

    class CategoriesViewHolder(
        private val binding: DiscoverItemBinding,
        private val context: Context
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Category) {
            binding.apply {
                binding.categoryTextView.text = item.name
                binding.categoryRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.categoryRecyclerView.addItemDecoration(RecyclerItemDecoration(8))
                binding.categoryRecyclerView.adapter = UserAdapter(item.users.data)
            }
        }
    }
}