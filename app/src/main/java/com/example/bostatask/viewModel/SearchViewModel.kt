package com.example.bostatask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bostatask.Model.City
import com.example.bostatask.Model.Irepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo: Irepo) : ViewModel() {

    private val _cities  = MutableStateFlow<List<City>>(emptyList())
    val cities : StateFlow<List<City>> =_cities

    fun getCities(){
        viewModelScope.launch {
          repo.getCities().collect{
              _cities.value = it
          }
        }
    }
}