package com.example.reachnetwork.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reachnetwork.model.CategoriesResponse
import com.example.reachnetwork.model.Category
import com.example.reachnetwork.model.OfferResponse
import com.example.reachnetwork.repo.DataRepository
import kotlinx.coroutines.*

class CustomViewModel: ViewModel() {

    private val repo = DataRepository
    val categoriesLiveData = MutableLiveData<CategoriesResponse>()
    val filteredUsersLiveData = MutableLiveData<List<Category>>()
    val offersLiveData = MutableLiveData<OfferResponse>()

    fun fetchDataFromAPI() {

        var categoriesBaseURL: String
        var categoriesEndPoint: String
        var offersBaseURL: String
        var offersEndPoint: String

        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.fetchUsageData()
            withContext(Dispatchers.Main) {
                val categoriesURL = response.item[0].request.url
                val timeLineURL = response.item[1].request.url
                val categories = getAPIDetails(categoriesURL)
                val offers = getAPIDetails(timeLineURL)
                categoriesBaseURL = categories[0]
                categoriesEndPoint = categories[1]
                offersBaseURL = offers[0]
                offersEndPoint = offers[1]
            }

            val categoryJob = fetchCategoriesFromAPIAsync(categoriesBaseURL, categoriesEndPoint)
            val offerJob = fetchOffersFromAPIAsync(offersBaseURL, offersEndPoint)
            populateData(categoryJob.await(), offerJob.await())
        }
    }

    private fun fetchCategoriesFromAPIAsync(baseURL: String, endPoint: String) =
        viewModelScope.async {
            repo.fetchCategories(baseURL, endPoint)
        }

    private fun fetchOffersFromAPIAsync(baseURL: String, endPoint: String) =
        viewModelScope.async {
            repo.fetchOffers(baseURL, endPoint)
        }


    private fun populateData(categoriesResponse: CategoriesResponse, offerResponse: OfferResponse) {
        categoriesLiveData.postValue(categoriesResponse)
        offersLiveData.postValue(offerResponse)
    }

    private fun getAPIDetails(url: String): List<String> {

        val index = url.indexOf('/', 8)
        val baseURL = url.substring(0, index + 1)
        val endPoint = url.substring(index + 1, url.length)
        return listOf(baseURL, endPoint)
    }

        fun filterUsers(searchKey: String) {

        if (searchKey == "")
            filteredUsersLiveData.value = categoriesLiveData.value?.data
        else {
            filteredUsersLiveData.value = categoriesLiveData.value?.data?.filter { item ->
                item.users.data.any { it.username.contains(searchKey) }
            }
        }
    }
//    fun filterUsers(searchKey: String) {
//
//        var filtered: List<Category>? = categoriesLiveData.value?.data
//        if (searchKey != ""){
//            filtered?.forEach { it.users.data.minus(it.users.data.filter { user -> !user.username.contains(searchKey) }) }
//            if (filtered != null) {
//                for (category in filtered) {
//                    category.users.data.minus(category.users.data.filter { user -> !user.username.contains('b') })
//                }
//            }
//        }
//
//
//        filteredUsersLiveData.value = filtered
//
//    }
}