package org.emunix.weather.ui.forecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.forecast_fragment.*
import org.emunix.weather.R
import java.lang.IllegalArgumentException


class ForecastFragment : Fragment() {

    private lateinit var city: String
    private lateinit var viewModel: ForecastViewModel

    private lateinit var viewAdapter: ForecastAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.forecast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)

        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
        recycler.addItemDecoration(dividerItemDecoration)
        recycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewAdapter = ForecastAdapter()
        recycler.adapter = viewAdapter

        viewModel.getForecast().observe(requireActivity(), Observer { forecast ->
            if (forecast != null)
                viewAdapter.setItems(forecast.forecastData)
        })

        viewModel.getMessageToUser().observe(requireActivity(), Observer { message ->
            if (message.isNotBlank())
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        })

        viewModel.getShowProgress().observe(requireActivity(), Observer { showProgress ->
            when(showProgress){
                true  -> progress.visibility = View.VISIBLE
                false -> progress.visibility = View.INVISIBLE
            }
        })
    }

    override fun onStart() {
        super.onStart()
        city = arguments?.getString("city") ?: throw IllegalArgumentException("city not passed to forecast fragment")
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadForecast(city)
    }
}