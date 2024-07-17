package com.task.composetask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.task.composetask.ui.theme.feature.homescreen.MedicineItem
import com.task.composetask.ui.theme.feature.homescreen.MedicineViewModel
import com.task.composetask.ui.theme.feature.homescreen.MedicinesScreen
import com.task.composetask.ui.theme.feature.login.LoginScreen
import com.task.composetask.ui.theme.feature.login.LoginViewModel
import com.task.composetask.ui.theme.feature.medicinescreen.MedicineDetailScreen
import com.task.composetask.utils.NavScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = NavScreen.LoginScreen.route) {
                composable(NavScreen.LoginScreen.route) {
                    LoginScreen(viewModel = hiltViewModel(), navController = navController)
                }
                composable(
                    NavScreen.MedicinesScreen.route,
                    arguments = listOf(navArgument("username") { type = NavType.StringType })
                ) { backStackEntry ->
                    val username = backStackEntry.arguments?.getString("username")
                    username?.let {
                        val viewModel: MedicineViewModel = hiltViewModel()
                        MedicinesScreen(username = it, viewModel = viewModel, navController = navController)
                    }
                }
                composable(NavScreen.MedicineDetailScreen.route) {
                    val medicine = navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.get<MedicineItem>("medicine")
                    medicine?.let {
                        MedicineDetailScreen(medicine = it, navController = navController)
                    }
                }
            }
        }
    }
}
