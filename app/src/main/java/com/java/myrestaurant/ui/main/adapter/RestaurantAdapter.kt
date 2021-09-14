package com.java.myrestaurant.ui.main.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.java.myrestaurant.R
import com.java.myrestaurant.data.model.MenuModel.MenuItemsResponse
import com.java.myrestaurant.data.model.RestaurantModel.RestaurantsItemResponse


class RestaurantAdapter(
        context: Context,
        private val restaurantList: ArrayList<RestaurantsItemResponse>,
        private val menuList: ArrayList<MenuItemsResponse>,
        private val itemNameMap: HashMap<Int, ArrayList<String>>
) :
        RecyclerView.Adapter<RestaurantSearchDemoHolder>() {
    private val restaurantListCopy: ArrayList<RestaurantsItemResponse>
    private val inflater: LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantSearchDemoHolder {
        return RestaurantSearchDemoHolder(inflater.inflate(R.layout.layout_restaurant_item, parent, false))
    }

    override fun onBindViewHolder(holder: RestaurantSearchDemoHolder, position: Int) {
        holder.bind(restaurantList[position])
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    /**
     *     Performs filter on recycler view items...
     */
    @SuppressLint("NewApi")
    fun filter(sequence: CharSequence?) {
//        Temp array list to store the searched item
        var tempRest: ArrayList<RestaurantsItemResponse> = ArrayList()
        val tempMenu: ArrayList<RestaurantsItemResponse> = ArrayList()

        if (!TextUtils.isEmpty(sequence)) {
            for (item in restaurantList) {
                if (item.name.toLowerCase()
                                .contains(sequence.toString().toLowerCase()) || item.address.toLowerCase()
                                .contains(
                                        sequence.toString().toLowerCase()
                                ) || item.cuisine_type.toLowerCase()
                                .contains(sequence.toString().toLowerCase())
                ) {
                    tempRest.add(item)
                }
            }
            if (tempRest.size == 0) {
                for (item in menuList) {
                    if (item.name.toLowerCase().contains(sequence.toString().toLowerCase())) {
                        itemNameMap.forEach { i, arrayList ->
                            arrayList.forEachIndexed { index, s ->
                                if (s.toLowerCase().equals(item.name.toLowerCase())) {
                                    restaurantList.forEachIndexed { index, restaurantsItemResponse ->
                                        if (restaurantsItemResponse.id.equals(i)) {
                                            if (!tempMenu.contains(restaurantsItemResponse))
                                                tempMenu.add(restaurantsItemResponse)
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        } else {
            tempRest.addAll(restaurantListCopy)
        }
        restaurantList.clear()
        if (tempRest.size == 0 || tempRest.isEmpty()) {
            tempRest = tempMenu
        }
        restaurantList.addAll(tempRest)
        notifyDataSetChanged()
        tempRest.clear()
    }

    init {
        restaurantListCopy = ArrayList()
        restaurantListCopy.addAll(restaurantList)
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
}