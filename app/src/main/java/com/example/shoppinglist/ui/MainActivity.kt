package com.example.shoppinglist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



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