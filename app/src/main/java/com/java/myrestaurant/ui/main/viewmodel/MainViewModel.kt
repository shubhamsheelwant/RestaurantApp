package com.java.myrestaurant.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.java.myrestaurant.data.model.MenuModel.MenusResponse
import com.java.myrestaurant.data.model.RestaurantModel.RestaurantResponse
import com.java.myrestaurant.data.model.RestaurantModel.RestaurantsItemResponse
import com.java.myrestaurant.data.repo.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    var menuResponse = MutableLiveData<MenusResponse>()
    var errorResponse = MutableLiveData<String>()
    var restaurantResponse = MutableLiveData<RestaurantResponse>()

    /**
     * this method will get the menu data from repository
     */
    fun getMenuData(menuJson: String?) {
        viewModelScope.launch {
            val result = mainRepository.getMenuData(menuJson!!)
            if (result != null) {
                menuResponse.value = result
            } else {
                errorResponse.value = "Please try after some time"
            }
        }
    }

    /**
     * this method will get the restaurant data from repository
     */
    fun getRestaurantData(restaurantJson: String?) {
        viewModelScope.launch {
            val result = mainRepository.getRestaurantData(restaurantJson!!)
            if (result != null) {
                restaurantResponse.value = result
            } else {
                errorResponse.value = "Please try after some time"
            }
        }
    }
}