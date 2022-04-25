package com.example.clima.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.example.clima.databinding.FragmentHomeBinding
import com.example.clima.ui.MainViewModel
import com.example.clima.util.Constants.API_KEY

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        mainViewModel.myWeatherResponse.value.toString()

        binding.button.setOnClickListener {
            var city = binding.editTextTextPersonName.text.toString()
            binding.cityNameText.text = binding.editTextTextPersonName.text
            mainViewModel.getClima(city)
            Toast.makeText(this.context, "Get Feito com sucesso", Toast.LENGTH_SHORT).show()
        }

        mainViewModel.myWeatherResponse.observe(viewLifecycleOwner) {
            response -> Log.d("Requisicao", response.body().toString())
        }

        return binding.root
    }
}