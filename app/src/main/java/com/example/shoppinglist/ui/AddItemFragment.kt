package com.example.shoppinglist.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.shoppinglist.MyApp
import com.example.shoppinglist.R
import com.example.shoppinglist.data.ShoppingItemModel
import com.example.shoppinglist.databinding.FragmentAddItemBinding
import com.example.shoppinglist.domain.ShoppingListViewModel
import com.example.shoppinglist.domain.ShoppingListViewModelFactory
import java.lang.Thread.sleep

private const val GALLERY_REQUEST = 202


class AddItemFragment : Fragment() {

    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoppingListViewModel by activityViewModels() {
        ShoppingListViewModelFactory(
            (requireActivity().application as MyApp).shoppingItemRepository
        )
    }

    lateinit var imagePickerActivityResult: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddItemBinding.inflate(inflater, container, false)

        imagePickerActivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result != null){
                //link where image is store in the phone storage
                val imageUri: Uri? = result.data?.data
                Glide.with(this)
                    .load(imageUri)
                    .into(binding.addImageImageView)
            }
        }
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

            addImageImageView.setOnClickListener {
                getImageFromGallery()
            }

            underImageTextView.setOnClickListener {
                getImageFromGallery()
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
            if(amount > 1 ) {
                amount--
                amountTextView.text = amount.toString()
            }
        }
    }

    private fun getImageFromGallery(){
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"


        imagePickerActivityResult.launch(photoPickerIntent)
    }

    private fun createItemToSave(): ShoppingItemModel? {
        return with(binding){
            ShoppingItemModel(
                title = titleEditText.text.toString(),
                id = System.currentTimeMillis(),
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