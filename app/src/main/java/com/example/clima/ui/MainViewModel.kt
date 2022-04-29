package com.example.clima.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clima.data.repository.Repository
import com.example.clima.ui.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.absoluteValue

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var _myWeatherResponse = MutableLiveData<Response<WeatherResponse>>()
    val myWeatherResponse: LiveData<Response<WeatherResponse>> = _myWeatherResponse



    fun getWeatherData(city: String) {
        viewModelScope.launch{
            try{
                val response = repository.getWeatherData(city)
                _myWeatherResponse.value = response
                Log.d("Sucesso", response.body().toString())
            }catch(e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

}