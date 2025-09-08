package com.example.shoppinglist.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShoppingItemModel
import com.example.shoppinglist.model.ShoppingItemRepository
import java.lang.Thread.sleep

class ShoppingListViewModel(
    val repository: ShoppingItemRepository
) : ViewModel() {

    val loadedDataFromServer: MutableLiveData<String> = MutableLiveData()

    fun addItem(item: ShoppingItemModel){
        repository.addItem(item)
    }

    fun deleteItem(item: ShoppingItemModel){
        repository.removeItem(item)
    }

    fun clearShoppingList(){
        repository.clearShoppingList()
    }

    fun loadDataFromServer(s: String) {
        Thread(Runnable {
            sleep(1500)
            loadedDataFromServer.postValue(s)
        }).start()
    }
}