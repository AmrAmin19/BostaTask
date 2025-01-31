package com.example.bostatask

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.bostatask.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getCities()
        lifecycleScope.launch {
            viewModel.cities.collect{
              if (it.isNotEmpty())
              {
                  Log.d("AmrTest", " ${it.size} :  ${it.first().cityName} ${it.first().cityId}  ${it.first().districts.first()} }" +
                          " ${it.last().cityName} ${it.last().cityId} ${it.last().districts.first()} } "  )
              }
            }
        }
    }
}