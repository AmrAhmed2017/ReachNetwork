package com.example.reachnetwork.viewmodel

import androidx.lifecycle.ViewModel
import com.example.reachnetwork.repo.DataRepository

class CustomViewModel: ViewModel() {

    private val repo = DataRepository

    fun fetchData(){
        repo.fetchData()
    }
}