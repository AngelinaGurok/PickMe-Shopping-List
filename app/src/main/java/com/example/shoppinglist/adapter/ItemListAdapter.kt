package com.example.shoppinglist.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.BoringLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.ShoppingItemModel
import com.example.shoppinglist.databinding.ShoppingListImageItemBinding
import com.example.shoppinglist.databinding.ShoppingListTextItemBinding

class ItemListAdapter(
    val list: ArrayList<ShoppingItemModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class ImageItemViewHolder(val bindingImageItem: ShoppingListImageItemBinding) :
        RecyclerView.ViewHolder(bindingImageItem.root){
            fun bindImageItem(item: ShoppingItemModel){
            with(bindingImageItem){
                titleImageItemTextView.text = item.title
                imageItemDescriptionTextView.text = item.description
                item.image?.let { itemImageItemImageView.setImageResource(it) }
                imageItemCheckBox.isChecked = item.isPurchased
            }
        }
    }

    class TextItemViewHolder(val bindingTextItem: ShoppingListTextItemBinding) :
        RecyclerView.ViewHolder(bindingTextItem.root){
            fun bindTextItem(item: ShoppingItemModel){
                with(bindingTextItem){
                    titleTextItemTextView.text = item.title
                    textItemDescriptionTextView.text = item.description
                    textItemCheckBox.isChecked = item.isPurchased
                }
            }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_IMAGE_ITEM){
            val bindingImageItem = ShoppingListImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            ImageItemViewHolder(bindingImageItem)
        } else {
            val bindingTextItem = ShoppingListTextItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            TextItemViewHolder(bindingTextItem)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].getItemType()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = list[position]
        if(list[position].getItemType() == VIEW_TYPE_IMAGE_ITEM){
            (holder as ImageItemViewHolder).bindImageItem(currentItem)
        } else{
            (holder as TextItemViewHolder).bindTextItem(currentItem)
        }


    }

    companion object{
        const val VIEW_TYPE_IMAGE_ITEM = 1
        const val VIEW_TYPE_TEXT_ITEM = 2
    }
}