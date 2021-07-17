package com.example.reachnetwork.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.reachnetwork.model.CategoriesResponse
import com.example.reachnetwork.model.OfferResponse
import com.example.reachnetwork.retrofit.RetrofitClient
import kotlinx.coroutines.*

object DataRepository{

    var categoriesLiveData = MutableLiveData<CategoriesResponse>()
    var offersLiveData = MutableLiveData<OfferResponse>()
    fun fetchData() {
        fetchDataFromAPI()
    }

    private fun fetchDataFromAPI() {

        var categoriesBaseURL: String
        var categoriesEndPoint: String
        var offersBaseURL: String
        var offersEndPoint: String

        val api = RetrofitClient().getRetrofitClient("https://www.getpostman.com")
         CoroutineScope(Dispatchers.IO).launch {
            val response = api.getUsageData()
            withContext(Dispatchers.Main) {
               Log.v("===", response.item.get(0).name)
                val categoriesURL = response.item.get(0).request.url
                val timeLineURL = response.item.get(1).request.url
                val categories = getAPIDetails(categoriesURL)
                val offers = getAPIDetails(timeLineURL)
                categoriesBaseURL = categories.get(0)
                categoriesEndPoint = categories.get(1)
                offersBaseURL = offers.get(0)
                offersEndPoint = offers.get(1)
            }

             val categoriesResponse = RetrofitClient().getRetrofitClient(categoriesBaseURL).getCategories(categoriesEndPoint)

             withContext(Dispatchers.Main) {
                 categoriesLiveData.value = categoriesResponse
//                 val s = categoriesResponse.data.get(0).name
//                 Log.v("====", "$s")
        }

             val offersResponse = RetrofitClient().getRetrofitClient(offersBaseURL).getOffers(offersEndPoint)

             withContext(Dispatchers.Main) {
                 offersLiveData.value = offersResponse
//                 val s = offersResponse.data.offers.data.get(0).cta_url
//                 Log.v("====", "$s")
             }
         }
    }
}

private fun getAPIDetails(url: String): List<String> {

    val index = url.indexOf('/', 8)
    val baseURL = url.substring(0, index + 1)
    val endPoint = url.substring(index + 1, url.length)
    return listOf(baseURL, endPoint)
}


