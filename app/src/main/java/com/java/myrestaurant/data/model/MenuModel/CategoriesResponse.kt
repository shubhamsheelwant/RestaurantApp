package com.java.myrestaurant.data.model.MenuModel

import com.google.gson.annotations.SerializedName

data class CategoriesResponse (
	val id : Int,
	val name : String,
	@SerializedName("menu-items") val menuItems : List<MenuItemsResponse>
)