package com.example.clima.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.clima.R
import com.example.clima.databinding.FragmentHomeBinding
import com.example.clima.ui.MainViewModel
import com.example.clima.model.WeatherResponse


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
            binding.cityInputText.text = null
        }

        mainViewModel.myWeatherResponse.observe(viewLifecycleOwner) { response ->
            Log.d("Requisicao", response.body().toString())

            if(isValidResponse(response.body())) {
                binding.cityNameText.text = response.body()?.name.toString()
                binding.temperatureText.text = formatText(response.body()?.main?.temp.toString(), "ºC")
                binding.iconImage.setImageResource(setIcon(response.body()!!.weather[0].id))
                binding.textDescription.text = response.body()!!.weather[0].description
                binding.cityInputText.error = null
                binding.ventoText.text = formatText(response.body()?.wind?.speed.toString(), "m/s")
                binding.humidadeText.text = formatText(response.body()?.main?.humidity.toString(), "%")
            } else {
                binding.cityInputText.error = "Cidade não foi encontrada"
                Toast.makeText(context, "Por favor digite novamente", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    private fun formatText(text: String, addText: String): String {
        return "$text $addText"
    }

    private fun isValidResponse(response: WeatherResponse?): Boolean {
        return response != null
    }

    private fun setIcon(id: Int): Int {
        return when(id) {
            in 200..232 -> R.drawable.icon_tempestade
            in 300..321 -> R.drawable.icon_chuva
            in 500..504 -> R.drawable.icon_chuva
            511 -> R.drawable.icon_neve
            in 521..531 -> R.drawable.icon_chuva
            in 600..622 -> R.drawable.icon_neve
            in 701..781 -> R.drawable.icon_nublado
            800 -> R.drawable.icon_dia_limpo
            in 801..804 -> R.drawable.icon_dia_nuvem

            else -> R.drawable.icon_erro
        }

    }


}