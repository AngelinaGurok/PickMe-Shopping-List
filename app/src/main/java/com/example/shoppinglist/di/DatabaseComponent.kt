package com.example.shoppinglist.di

import com.example.shoppinglist.ui.AddItemFragment
import com.example.shoppinglist.ui.MainScreenFragment
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    fun inject(fragment: MainScreenFragment)

    fun inject(fragment: AddItemFragment)
}