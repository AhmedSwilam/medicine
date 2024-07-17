package com.task.composetask.utils

import android.net.Uri
import com.task.composetask.ui.theme.feature.homescreen.MedicineItem

sealed class NavScreen(val route: String) {
    object LoginScreen : NavScreen("login")
    object MedicinesScreen : NavScreen("medicines/{username}") {
        fun createRoute(username: String) = "medicines/$username"
    }
    object MedicineDetailScreen : NavScreen("medicineDetail")
}
