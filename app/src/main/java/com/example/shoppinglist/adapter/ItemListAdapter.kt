package com.example.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.data.ShoppingItemModel
import com.example.shoppinglist.databinding.ShoppingListImageItemBinding
import com.example.shoppinglist.databinding.ShoppingListTextItemBinding

class ItemListAdapter(
    var list: List<ShoppingItemModel>,
    val changeItemStatusInStorageToPurchased: (ShoppingItemModel) -> Unit,
    val changeItemStatusInStorageToNotPurchased: (ShoppingItemModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class ImageItemViewHolder(val bindingImageItem: ShoppingListImageItemBinding) :
        RecyclerView.ViewHolder(bindingImageItem.root){

            fun bindImageItem(item: ShoppingItemModel){

            with(bindingImageItem){

                titleImageItemTextView.text = item.title
                imageItemDescriptionTextView.text = item.description
                item.image?.let { itemImageItemImageView.setImageResource(it) }
                amountCountTextView.text = item.amount.toString()
                imageItemCheckBox.isChecked = item.isPurchased

                imageItemCheckBox.setOnClickListener {
                    if(imageItemCheckBox.isChecked){
                        changeItemStatusInStorageToPurchased(item.copy(
                            isPurchased = true
                        ))
                    } else {
                        changeItemStatusInStorageToNotPurchased(item.copy(
                            isPurchased = false
                        ))
                    }
                }

                if(imageItemCheckBox.isChecked){
                    isInCartTextView.visibility = View.VISIBLE
                } else {
                    isInCartTextView.visibility = View.GONE
                }

            }
        }

    }

    inner class TextItemViewHolder(val bindingTextItem: ShoppingListTextItemBinding) :
        RecyclerView.ViewHolder(bindingTextItem.root){

            fun bindTextItem(item: ShoppingItemModel){

                with(bindingTextItem){

                    titleTextItemTextView.text = item.title
                    textItemDescriptionTextView.text = item.description
                    amountCountTextView.text = item.amount.toString()
                    textItemCheckBox.isChecked = item.isPurchased

                    textItemCheckBox.setOnClickListener {
                        if(textItemCheckBox.isChecked){
                            changeItemStatusInStorageToPurchased(item.copy(
                                isPurchased = true
                            ))

                        } else {
                            changeItemStatusInStorageToNotPurchased(item.copy(
                                isPurchased = false
                            ))
                        }
                    }

                    if(textItemCheckBox.isChecked){
                        isInCartTextView.visibility = View.VISIBLE
                    } else {
                        isInCartTextView.visibility = View.GONE
                    }
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

    fun setNewDataSet(list: List<ShoppingItemModel>){
        this.list = list
        notifyDataSetChanged()
    }

    companion object{
        const val VIEW_TYPE_IMAGE_ITEM = 1
        const val VIEW_TYPE_TEXT_ITEM = 2
    }
}