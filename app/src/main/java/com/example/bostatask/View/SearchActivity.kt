package com.example.bostatask.View

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bostatask.Model.ApiState
import com.example.bostatask.Model.City
import com.example.bostatask.R
import com.example.bostatask.databinding.ActivitySearchBinding
import com.example.bostatask.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    val viewModel: SearchViewModel by viewModels()
    lateinit var binding: ActivitySearchBinding

    lateinit var adapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CityAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.getCities()

        lifecycleScope.launch {
            viewModel.filteredCities.collect { cities ->
                adapter.submitList(cities)
            }
        }

        setupSearch()

        lifecycleScope.launch {
            viewModel.cities.collect{ resource ->
                when (resource) {
                    is ApiState.Loading -> {showLoading(true)}
                    is ApiState.Success<List<City>> -> {
                        showLoading(false)
                        adapter.submitList(resource.data)
                       // Log.d("AmrTest", resource.data.first().cityName)
                    }
                    is ApiState.Error -> {
                        showLoading(false)
                        Toast.makeText(this@SearchActivity, resource.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }



    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateSearchQuery(newText ?: "")
                return true
            }
        })
    }


    private fun showLoading(isLoading: Boolean) {
         binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}