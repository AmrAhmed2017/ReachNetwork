package com.example.reachnetwork.retrofit

import com.example.reachnetwork.model.CategoriesResponse
import com.example.reachnetwork.model.OfferResponse
import com.example.reachnetwork.model.PostmanResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

public interface Api {

    @GET("collections/2fae51e913f74438dd96")
    suspend fun getUsageData(): PostmanResponse

    @GET
    suspend fun getCategories(@Url endPoint: String): CategoriesResponse

    @GET
    suspend fun getOffers(@Url endPoint: String): OfferResponse

}