package com.example.shoppinglist.domain

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShoppingItemModel
import com.example.shoppinglist.model.ShoppingItemRepository

class ShoppingListViewModel(
    val repository: ShoppingItemRepository
) : ViewModel() {

    fun addItem(item: ShoppingItemModel) {
        repository.addItem(item)
    }

    fun deleteItem(item: ShoppingItemModel) {
        repository.removeItem(item)
    }

    fun clearShoppingList() {
        repository.clearShoppingList()
    }

    fun getAll(): List<ShoppingItemModel> {
        return  repository.getAll()
    }
}