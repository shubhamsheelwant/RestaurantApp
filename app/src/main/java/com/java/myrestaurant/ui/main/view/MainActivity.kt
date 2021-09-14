package com.java.myrestaurant.ui.main.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.java.myrestaurant.R
import com.java.myrestaurant.data.model.MenuModel.MenuItemsResponse
import com.java.myrestaurant.data.model.MenuModel.MenusResponse
import com.java.myrestaurant.data.model.RestaurantModel.RestaurantResponse
import com.java.myrestaurant.data.model.RestaurantModel.RestaurantsItemResponse
import com.java.myrestaurant.ui.base.ViewModelFactory
import com.java.myrestaurant.ui.main.adapter.RestaurantAdapter
import com.java.myrestaurant.ui.main.viewmodel.MainViewModel
import com.java.myrestaurant.utility.ReadInputStream.Companion.inputStreamToString
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    var restaurantModel: RestaurantResponse? = null
    var menuModel: MenusResponse? = null
    var map = HashMap<Int, ArrayList<String>>()

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory()
        ).get(MainViewModel::class.java)
        inilializeDataModels()
        initObserver()
    }

    /**
     * This method is used to observe the changes.
     */
    private fun initObserver() {
        viewModel.apply {
            errorResponse.observe(this@MainActivity, androidx.lifecycle.Observer {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                Log.i(TAG, "shubh: " + it)
            })
            menuResponse.observe(this@MainActivity, androidx.lifecycle.Observer {
                Log.i(TAG, "shubh: " + it.menus)
                menuModel = it
                initMap()
            })
            restaurantResponse.observe(this@MainActivity, androidx.lifecycle.Observer {
                Log.i(TAG, "shubh: " + it)
                restaurantModel = it
                initView()
            })
        }
    }

    /**
     * This method will initialise the view and will handle the different events.
     */
    private fun initView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rcview)

        val adapter = RestaurantAdapter(
            this,
            restaurantModel!!.restaurants as ArrayList<RestaurantsItemResponse>,
            menuModel!!.menus.first().categories.first().menuItems as ArrayList<MenuItemsResponse>,
            map
        )
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setLayoutManager(mLayoutManager)
        recyclerView.adapter = adapter

        val etSearch = findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                adapter.filter(s)
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    /**
     * this method will initialise the data models.
     * reading data from json.
     */
    private fun inilializeDataModels() {
        val restaurantJson =
            inputStreamToString(resources.openRawResource(R.raw.restaurants))
        viewModel.getRestaurantData(restaurantJson)

        val menuJson =
            inputStreamToString(resources.openRawResource(R.raw.menus))
        viewModel.getMenuData(menuJson)
    }

    fun initMap(){
        menuModel!!.menus.forEachIndexed { index, menu ->
            val itemNameList = ArrayList<String>()
            menu.categories.forEachIndexed { index, categoriesResponse ->
                categoriesResponse.menuItems.forEachIndexed { index, menuItemsResponse ->
                    itemNameList.add(menuItemsResponse.name.toLowerCase())
                }
            }
            map.put(menu.restaurantId,itemNameList)
        }
    }

}