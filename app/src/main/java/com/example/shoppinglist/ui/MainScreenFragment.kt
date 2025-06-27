package com.example.shoppinglist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.adapter.ItemListAdapter
import com.example.shoppinglist.databinding.MainScreenFragmentBinding
import com.example.shoppinglist.utils.Utils.dataSet

class MainScreenFragment : Fragment() {
    private var _binding: MainScreenFragmentBinding? = null
    private val binding get() = _binding!!
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
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViews(){
        binding.shoppingListRecyclerView.adapter = ItemListAdapter(dataSet)

        //fragment has no context, so we use require context from the activity to which the
        //fragment is attached
        binding.shoppingListRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.addButton.setOnClickListener{
            findNavController().navigate(R.id.action_mainScreenFragment_to_addItemFragment)
        }
    }
}