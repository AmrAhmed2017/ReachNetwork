package com.example.reachnetwork.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.reachnetwork.R
import com.example.reachnetwork.viewmodel.CustomViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: CustomViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(CustomViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }
}