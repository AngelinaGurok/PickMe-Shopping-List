package com.example.shoppinglist

import android.app.Application
import com.example.shoppinglist.data.ShoppingItemModel
import com.example.shoppinglist.di.DaggerDatabaseComponent
import com.example.shoppinglist.di.DatabaseComponent
import com.example.shoppinglist.model.ShoppingItemRepository
import com.example.shoppinglist.model.ShoppingItemRepositoryImplementation
import com.example.shoppinglist.utils.Utils.dataSet
import dagger.internal.DaggerGenerated

class MyApp: Application() {
    lateinit var databaseComponent : DatabaseComponent
    override fun onCreate() {
        super.onCreate()
        //shoppingItemRepository = ShoppingItemRepositoryImplementation( database = dataSet)
        databaseComponent = DaggerDatabaseComponent.create()
    }
}