package com.example.shoppinglist.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoppinglist.MyApp
import com.example.shoppinglist.data.ShoppingItemModel
import com.example.shoppinglist.databinding.FragmentAddItemBinding
import com.example.shoppinglist.domain.ShoppingListViewModel
import com.example.shoppinglist.domain.ShoppingListViewModelFactory
import java.lang.Thread.sleep


class AddItemFragment : Fragment() {

    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoppingListViewModel by activityViewModels() {
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
        Log.d("MyTag", "onViewCreated add fragment: ${viewModel.toString()}")
        setUpViews()
    }

    private fun setUpViews(){
        with(binding){
            decreaseAmountButton.setOnClickListener {
                decreaseAmount()
            }

            increaseAmountButton.setOnClickListener {
                increaseAmount()
            }

            confirmItemButton.setOnClickListener{
                val itemToSave = createItemToSave()
                if(itemToSave != null) {
                    viewModel.addItem(itemToSave)
                }
                //remove
                /*val textToSend = descriptionEditText.text.toString()
                viewModel.loadDataFromServer(textToSend)
                viewModel.loadedDataFromServer.observe(viewLifecycleOwner){ liveDataValue ->
                    binding.addItemTitleTextView.text = liveDataValue
                }*/

                findNavController().popBackStack()
            }

            returnToShoppingListButton.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

    private fun increaseAmount() {
        with(binding){
            var amount = amountTextView.text.toString().toInt()
            amount++
            amountTextView.text = amount.toString()
        }
    }

    private fun decreaseAmount() {
        with(binding){
            var amount = amountTextView.text.toString().toInt()
            if(amount > 0 ) {
                amount--
                amountTextView.text = amount.toString()
            }
        }
    }

    private fun createItemToSave(): ShoppingItemModel? {
        return with(binding){
            ShoppingItemModel(
                id = System.currentTimeMillis(),
                title = titleEditText.text.toString(),
                amount = amountTextView.text.toString().toInt(),
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