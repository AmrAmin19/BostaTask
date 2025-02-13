package com.example.bostatask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bostatask.Model.ApiState
import com.example.bostatask.Model.City
import com.example.bostatask.Model.Irepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo: Irepo) : ViewModel() {

    private val _cities  = MutableStateFlow<ApiState<List<City>>>(ApiState.Loading)
    val cities : StateFlow<ApiState<List<City>>> =_cities

    private val _filteredCities = MutableStateFlow<List<City>>(emptyList())
    val filteredCities: StateFlow<List<City>> = _filteredCities

    private val searchFlow = MutableSharedFlow<String>(replay = 1)


    init {
        // Collect search queries and filter the list
        viewModelScope.launch {
            searchFlow.collectLatest { query ->
                filterCities(query)
            }
        }
    }

    fun updateSearchQuery(query: String) {
        viewModelScope.launch {
            searchFlow.emit(query.trim().lowercase())
        }
    }



    private fun filterCities(query: String) {
        val allCities = (_cities.value as? ApiState.Success)?.data ?: emptyList()
        _filteredCities.value = if (query.isNotEmpty()) {
            allCities.filter { it.cityName.lowercase().startsWith(query) }
        } else {
            allCities
        }
    }





    fun getCities(){
        viewModelScope.launch {
          repo.getCities().collect{
              _cities.value = it
          }
        }
    }
}