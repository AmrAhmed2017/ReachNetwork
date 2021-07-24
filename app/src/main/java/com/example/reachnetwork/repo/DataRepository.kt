package com.example.reachnetwork.repo

import com.example.reachnetwork.retrofit.RetrofitClient

object DataRepository{

    suspend fun fetchUsageData() =
    RetrofitClient().getRetrofitClient("https://www.getpostman.com").getUsageData()

    suspend fun fetchCategories(baseURL: String, endPoint: String) =
        RetrofitClient().getRetrofitClient(baseURL).getCategories(endPoint)

    suspend fun fetchOffers(baseURL: String, endPoint: String) =
        RetrofitClient().getRetrofitClient(baseURL).getOffers(endPoint)

}


