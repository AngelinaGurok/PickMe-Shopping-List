package com.example.shoppinglist.data

import com.example.shoppinglist.adapter.ItemListAdapter.Companion.VIEW_TYPE_IMAGE_ITEM
import com.example.shoppinglist.adapter.ItemListAdapter.Companion.VIEW_TYPE_TEXT_ITEM
import java.io.Serializable

data class ShoppingItemModel(
    val title: String,
    val image: Int?,
    val amount: Int,
    val description: String?,
    var isPurchased: Boolean
) {
    fun getItemType(): Int{
        return if(image != null){
            VIEW_TYPE_IMAGE_ITEM
        } else
            VIEW_TYPE_TEXT_ITEM
    }
}