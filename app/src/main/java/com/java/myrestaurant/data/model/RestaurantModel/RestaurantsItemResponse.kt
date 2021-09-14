package com.java.myrestaurant.data.model.RestaurantModel

data class RestaurantsItemResponse (

    val id : Int,
    val name : String,
    val neighborhood : String,
    val photograph : String,
    val address : String,
    val latlng : LatlngResponse,
    val cuisine_type : String,
    val operating_hours : OperatingHoursResponse,
    val reviews : List<ReviewsResponse>
)