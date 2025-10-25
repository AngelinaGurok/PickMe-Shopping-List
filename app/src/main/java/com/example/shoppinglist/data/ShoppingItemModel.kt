package com.example.shoppinglist.data

import android.net.Uri
import com.example.shoppinglist.adapter.ItemListAdapter.Companion.VIEW_TYPE_IMAGE_ITEM
import com.example.shoppinglist.adapter.ItemListAdapter.Companion.VIEW_TYPE_TEXT_ITEM

data class ShoppingItemModel(
    val id: Long,
    val title: String,
    val image: Uri?,
    val amount: Int,
    val description: String?,
    var isPurchased: Boolean
) : Comparable<ShoppingItemModel> {

    fun getItemType(): Int{
        return if(image != null){
            VIEW_TYPE_IMAGE_ITEM
        } else
            VIEW_TYPE_TEXT_ITEM
    }

    override fun compareTo(other: ShoppingItemModel): Int {
        return compareValuesBy(this, other, {it.isPurchased}, {it.id})
    }


}