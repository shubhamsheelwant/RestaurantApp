package com.java.myrestaurant.data.model.MenuModel

data class MenuItemsResponse (

	val id : Int,
	val name : String,
	val description : String,
	val price : Double,
	val images : List<String>
)