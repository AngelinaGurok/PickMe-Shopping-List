package com.example.shoppinglist.di

import com.example.shoppinglist.data.ShoppingItemModel
import com.example.shoppinglist.model.ShoppingItemRepository
import com.example.shoppinglist.model.ShoppingItemRepositoryImplementation
import com.example.shoppinglist.utils.Utils.dataSet
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase() : ArrayList<ShoppingItemModel>{
        return dataSet
    }

    @Provides
    fun provideShoppingItemRepository(database: ArrayList<ShoppingItemModel>): ShoppingItemRepository{
        return ShoppingItemRepositoryImplementation(database)
    }
}