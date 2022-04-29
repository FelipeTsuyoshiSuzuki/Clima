package com.example.clima.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.clima.databinding.FragmentHomeBinding
import com.example.clima.ui.MainViewModel
import com.example.clima.ui.model.WeatherResponse


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.buttonSearch.setOnClickListener {
            val city = binding.cityInputText.text.toString()
            mainViewModel.getWeatherData(city)

        }

        mainViewModel.myWeatherResponse.observe(viewLifecycleOwner) { response ->
            Log.d("Requisicao", response.body().toString())

            if(isValidResponse(response.body())) {
                binding.cityNameText.text = response.body()?.name.toString()
                binding.temperatureText.text = formatTextTemperature(response.body()?.main?.temp.toString(), "ºC")
                binding.cityInputText.error = null
            } else {
                binding.cityNameText.text = "CIDADE"
                binding.temperatureText.text = "TEMPERATURA "
                binding.cityInputText.error = "Cidade Inválida"
                Toast.makeText(context, "Por favor insira uma cidade válida", Toast.LENGTH_LONG).show()
            }

        }

        return binding.root
    }

    private fun formatTextTemperature(text: String, addText: String): String {
        return "$text $addText"
    }

    private fun isValidResponse(response: WeatherResponse?): Boolean {
        return response != null
    }

}