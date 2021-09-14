package com.java.myrestaurant.data.repo

import com.google.gson.Gson
import com.java.myrestaurant.data.model.MenuModel.MenusResponse
import com.java.myrestaurant.data.model.RestaurantModel.RestaurantResponse

class MainRepository() {

    suspend fun getMenuData(menuJson: String): MenusResponse{
        val menuModel = Gson().fromJson(menuJson, MenusResponse::class.java)
        return menuModel
    }

    suspend fun getRestaurantData(restaurantJson: String): RestaurantResponse {
        val restaurantModel = Gson().fromJson(restaurantJson, RestaurantResponse::class.java)
        return restaurantModel
    }
}