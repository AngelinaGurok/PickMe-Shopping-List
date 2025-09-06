package com.example.shoppinglist.model

import com.example.shoppinglist.data.ShoppingItemModel

class ShoppingItemRepositoryImplementation(
    val database: ArrayList<ShoppingItemModel>
) : ShoppingItemRepository{
    override fun addItem(item: ShoppingItemModel) {
        database.add(item)
    }

    override fun getItemByTitle(title: String) : ShoppingItemModel? {
        return database.find {
            it.title == title
        }
    }

    override fun updateItem(itemToUpdate: ShoppingItemModel) {
        val item = database.find {
            it.id== itemToUpdate.id
        }
        if(item != null){
            database.remove(item)
            database.add(itemToUpdate)
        }
    }

    override fun removeItem(itemToRemove: ShoppingItemModel) {
        database.remove(itemToRemove)
    }

    override fun clearShoppingList() {
        database.clear()
    }

    override fun getAll() = database.toList()

}