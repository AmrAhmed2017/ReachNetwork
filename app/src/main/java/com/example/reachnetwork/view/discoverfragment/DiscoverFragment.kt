package com.example.reachnetwork.view.discoverfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reachnetwork.R
import com.example.reachnetwork.adapter.CategoriesAdapter
import com.example.reachnetwork.adapter.OffersAdapter
import com.example.reachnetwork.databinding.FragmentDiscoverBinding
import com.example.reachnetwork.util.RecyclerItemDecoration
import com.example.reachnetwork.viewmodel.CustomViewModel

class DiscoverFragment : Fragment() {

    private lateinit var viewModel: CustomViewModel
    private lateinit var binding: FragmentDiscoverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(CustomViewModel::class.java)
        viewModel.fetchDataFromAPI()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_discover, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.discoverRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity())
        binding.discoverRecyclerView.addItemDecoration(RecyclerItemDecoration(8))

        binding.offersRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.offersRecyclerView.addItemDecoration(RecyclerItemDecoration(8))

        viewModel.categoriesLiveData.observe(viewLifecycleOwner, {
            binding.discoverRecyclerView.adapter = CategoriesAdapter(it.data, requireContext())
        })

        viewModel.offersLiveData.observe(viewLifecycleOwner, {
            binding.offersRecyclerView.adapter = OffersAdapter(it.data.offers.data)
        })

        viewModel.filteredUsersLiveData.observe(viewLifecycleOwner, {
            binding.discoverRecyclerView.adapter = CategoriesAdapter(it, requireContext())
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    viewModel.filterUsers(p0)
                }
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    viewModel.filterUsers(p0)
                }
                return false
            }
        })
    }
}