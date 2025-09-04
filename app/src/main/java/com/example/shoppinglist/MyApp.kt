package com.example.shoppinglist

import android.app.Application
import com.example.shoppinglist.data.ShoppingItemModel
import com.example.shoppinglist.model.ShoppingItemRepository
import com.example.shoppinglist.model.ShoppingItemRepositoryImplementation
import com.example.shoppinglist.utils.Utils.dataSet

class MyApp: Application() {
    lateinit var shoppingItemRepository : ShoppingItemRepository
    override fun onCreate() {
        super.onCreate()
        shoppingItemRepository = ShoppingItemRepositoryImplementation( database = dataSet)
    }
}