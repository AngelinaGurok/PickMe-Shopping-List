package com.example.shoppinglist.model

import com.example.shoppinglist.data.ShoppingItemModel

interface ShoppingItemRepository {

    fun addItem(item: ShoppingItemModel)

    fun getItemByTitle(title: String) : ShoppingItemModel?

    fun updateItem(itemToUpdate: ShoppingItemModel)

    fun removeItem(itemToRemove: ShoppingItemModel)

    fun clearShoppingList()

    fun getAll(): List<ShoppingItemModel>
}