package com.example.shoppinglist

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.adapter.ItemListAdapter
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.utils.Utils.dataSet

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shoppingListRecyclerView.adapter = ItemListAdapter(dataSet)
        binding.shoppingListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.addButton.setOnClickListener{

        }
      /*  binding.addButton.setOnClickListener{
            val intent = Intent(this, AddItemActivity::class.java)
            intent.putExtra("MyInt", 8)
            intent.putExtra("MyString", "data")
            val myModel = ShoppingItemImageModel(
                "My title",
                1234,
                4
            )

            val myParcelModel = ShoppingItemTextModel(
                "My title",
                "DESCRIPTION",
                4
            )
            intent.putExtra("MyObject", myModel)
            intent.putExtra("MyParcelObject", myParcelModel)
            startActivity(intent)
        }*/
    }
}