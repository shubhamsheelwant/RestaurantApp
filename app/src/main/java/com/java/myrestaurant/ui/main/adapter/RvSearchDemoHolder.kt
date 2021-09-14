package com.java.myrestaurant.ui.main.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.java.myrestaurant.R
import com.java.myrestaurant.data.model.RestaurantModel.RestaurantsItemResponse


class RestaurantSearchDemoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val restaurantName: TextView
    private val restaurantImage: ImageView
    private val restaurantNeighbourhood: TextView
    private val restaurantAddress: TextView
    private val restaurantCuisine: TextView

    fun bind(name: RestaurantsItemResponse) {
        restaurantName.text = name.name
        restaurantNeighbourhood.text = name.neighborhood
        restaurantAddress.text = name.address
        restaurantCuisine.text = name.cuisine_type
    }

    init {
        restaurantName = itemView.findViewById<TextView>(R.id.name)
        restaurantImage = itemView.findViewById<ImageView>(R.id.image)
        restaurantNeighbourhood = itemView.findViewById(R.id.neighbourhood)
        restaurantAddress = itemView.findViewById(R.id.address)
        restaurantCuisine = itemView.findViewById(R.id.cuisinType)
    }
}