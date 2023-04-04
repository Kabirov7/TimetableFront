package com.example.raspisanie

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.raspisanie.navigation.BottomItem
import com.example.raspisanie.screens.ViewLessons
import com.example.raspisanie.ui.theme.Blueee
import com.google.accompanist.pager.*
import com.mrerror.singleRowCalendar.SingleRowCalendar
import com.mrerror.singleRowCalendar.weekFinalDays
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import kotlinx.coroutines.launch
import java.util.*



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun ViewApp()
{
    val navController = rememberNavController()
    val listItems = listOf(
        BottomItem.HomeScreen,
        BottomItem.SearchScreen,
        BottomItem.ProfileScreen
    )

    Scaffold(
        bottomBar =
        {
            BottomNavigation(
                backgroundColor = Color.White
            ) {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route
                listItems.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconId),
                                contentDescription = "Icon"
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                fontSize = 9.sp
                            )
                        },
                        selectedContentColor = Blueee,
                        unselectedContentColor = Color.Gray,
                        alwaysShowLabel = false,
                    )
                }
            }
        }
    )
    {
        NavHost(navController = navController, startDestination = "home")
        {
            composable("home")
            {
               ViewLessons()
            }
            composable("search")
            {
                Text("Search", Modifier.fillMaxSize())
            }
            composable("profile")
            {
                Text("Profile", Modifier.fillMaxSize())
            }
        }
    }


    NavHost(navController = navController, startDestination = "home")
    {
        composable("home")
        {
            ViewLessons()
        }
        composable("search")
        {
            Text("Search", Modifier.fillMaxSize())
        }
        composable("profile")
        {
            Text("Profile", Modifier.fillMaxSize())
        }
    }


}