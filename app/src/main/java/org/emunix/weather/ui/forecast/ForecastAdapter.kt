package org.emunix.weather.ui.forecast


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.emunix.weather.R
import org.emunix.weather.data.Forecast
import java.text.SimpleDateFormat
import java.util.*


class ForecastAdapter: RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    private var forecast: List<Forecast.ForecastData> = emptyList()

    fun setItems(forecast: List<Forecast.ForecastData>) {
        this.forecast = forecast
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date = itemView.findViewById<TextView>(R.id.date)!!
        val temp = itemView.findViewById<TextView>(R.id.temp)!!
        val pressure = itemView.findViewById<TextView>(R.id.pressure)!!
        val humidity = itemView.findViewById<TextView>(R.id.humidity)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (forecast.isNotEmpty()) {
            holder.date.text = forecast[position].date
            holder.temp.text = forecast[position].temp.toString()
            holder.pressure.text = forecast[position].pressure.toString()
            holder.humidity.text = forecast[position].humidity.toString()
        }
    }

    override fun getItemCount(): Int {
        return forecast.size
    }
}