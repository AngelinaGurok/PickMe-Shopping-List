package com.example.shoppinglist.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.MyApp
import com.example.shoppinglist.data.ShoppingItemModel
import com.example.shoppinglist.databinding.FragmentAddItemBinding
import com.example.shoppinglist.domain.ShoppingListViewModel
import com.example.shoppinglist.domain.ShoppingListViewModelFactory


class AddItemFragment : Fragment() {

    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoppingListViewModel by viewModels {
        ShoppingListViewModelFactory(
            (requireActivity().application as MyApp).shoppingItemRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyTag", "onVeiwCreated: ${viewModel.toString()}")
        setUpViews()
    }

    private fun setUpViews(){
        with(binding){
            confirmItemButton.setOnClickListener{
                val itemToSave = createItemToSave()
                if(itemToSave != null) {
                    viewModel.addItem(itemToSave)
                }
                Log.d("MyTag", viewModel.getAll().toString())
            }
        }
    }

    private fun createItemToSave(): ShoppingItemModel? {
        return with(binding){
            ShoppingItemModel(
                id = System.currentTimeMillis(),
                title = titleEditText.text.toString(),
                amount = 1,
                description = descriptionEditText.text.toString(),
                isPurchased = false,
                image = null
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}