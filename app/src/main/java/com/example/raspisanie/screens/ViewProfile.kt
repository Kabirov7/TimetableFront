package com.example.raspisanie.screens

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun ViewProfile(navController: NavController)
{
    Button(onClick = { navController.navigate("login") }) {
    }
}