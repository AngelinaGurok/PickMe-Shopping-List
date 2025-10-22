package com.example.shoppinglist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.MyApp
import com.example.shoppinglist.R
import com.example.shoppinglist.adapter.ItemListAdapter
import com.example.shoppinglist.databinding.MainScreenFragmentBinding
import com.example.shoppinglist.domain.ShoppingListViewModel
import com.example.shoppinglist.domain.ShoppingListViewModelFactory
import com.example.shoppinglist.utils.Utils.dataSet

class MainScreenFragment : Fragment() {
    private var _binding: MainScreenFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShoppingListViewModel by activityViewModels() {
        ShoppingListViewModelFactory(
            (requireActivity().application as MyApp).shoppingItemRepository
        )
    }
    var mainRecyclerViewAdapter: ItemListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener{
            findNavController().navigate(R.id.action_mainScreenFragment_to_addItemFragment2)
        }
        Log.d("MyTag", "onViewCreated main: ${viewModel.toString()}")
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViews(){
        mainRecyclerViewAdapter = ItemListAdapter(
            list = viewModel.getAll(),
            changeItemStatusInStorageToPurchased = {item ->
                viewModel.updateItem(item)
            },
            changeItemStatusInStorageToNotPurchased = { item ->
                viewModel.updateItem(item)
            }
        )
        binding.shoppingListRecyclerView.adapter = mainRecyclerViewAdapter

        viewModel.databaseData.observe(viewLifecycleOwner){ dataSet ->
            mainRecyclerViewAdapter?.setNewDataSet(dataSet)
        }

        //fragment has no context, so we use require context from the activity to which the
        //fragment is attached
        binding.shoppingListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}