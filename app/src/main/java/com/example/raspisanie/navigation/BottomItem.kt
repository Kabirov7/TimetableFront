package com.example.raspisanie.navigation

import com.example.raspisanie.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String){
    object HomeScreen: BottomItem("Занятия", R.drawable.iconhome, "home")
    object SearchScreen: BottomItem("Поиск", R.drawable.iconsearch, "search")
    object ProfileScreen: BottomItem("Профиль", R.drawable.iconperson, "profile")
}
