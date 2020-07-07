package org.emunix.weather.ui.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.current_weather_fragment.*
import org.emunix.weather.R

class CurrentWeatherFragment : Fragment() {

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)

        viewModel.getWeather().observe(requireActivity(), Observer { weather ->
            temp.text = weather?.temp?.toString() ?: ""
            pressure.text = weather?.pressure?.toString() ?: ""
            humidity.text = weather?.humidity?.toString() ?: ""
        })

        viewModel.getMessageToUser().observe(requireActivity(), Observer { message ->
            if (message.isNotBlank())
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        })

        viewModel.getShowProgress().observe(requireActivity(), Observer { showProgress ->
            swipeRefreshLayout.isRefreshing = showProgress
        })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val choose = resources.getStringArray(R.array.cities)
                viewModel.userSelectCity(choose[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }

        forecastButton.setOnClickListener {
            findNavController().navigate(R.id.action_currentWeatherFragment_to_forecastFragment, bundleOf("city" to spinner.selectedItem.toString()))
        }

        swipeRefreshLayout.setOnRefreshListener { viewModel.userSelectCity(spinner.selectedItem.toString()) }

        viewModel.userSelectCity(spinner.selectedItem.toString())
    }
}