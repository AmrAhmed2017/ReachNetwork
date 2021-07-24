package com.example.reachnetwork.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.reachnetwork.model.CategoriesResponse
import com.example.reachnetwork.model.OfferResponse
import com.example.reachnetwork.retrofit.RetrofitClient
import kotlinx.coroutines.*

object DataRepository{

    suspend fun fetchUsageData() =
    RetrofitClient().getRetrofitClient("https://www.getpostman.com").getUsageData()

    suspend fun fetchCategories(baseURL: String, endPoint: String) =
        RetrofitClient().getRetrofitClient(baseURL).getCategories(endPoint)

    suspend fun fetchOffers(baseURL: String, endPoint: String) =
        RetrofitClient().getRetrofitClient(baseURL).getOffers(endPoint)

}


