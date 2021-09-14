package com.java.myrestaurant.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.java.myrestaurant.data.repo.MainRepository
import com.java.myrestaurant.ui.main.viewmodel.MainViewModel


class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository()) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}