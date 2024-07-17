package com.task.composetask.ui.theme.feature.homescreen

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.task.composetask.utils.NavScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicinesScreen(username: String, viewModel: MedicineViewModel, navController: NavController) {
    val medicines by viewModel.medicines.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        // Top section with username
        TopAppBar(
            title = { Text(text = "Welcome, $username!") },
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.topAppBarColors()
        )

        // Medicines list
        LazyColumn(
            modifier = Modifier.padding(top = AppBarHeight),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(medicines) { medicine ->
                MedicineCard(medicine = medicine) {
                    navController.currentBackStackEntry?.savedStateHandle?.set("medicine", medicine)
                    navController.navigate(NavScreen.MedicineDetailScreen.route)
                }
            }
        }
    }
}

private val AppBarHeight = 56.dp // Typical height of TopAppBar

@Composable
fun MedicineCard(medicine: MedicineItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${medicine.name}", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Dose: ${medicine.dose}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Strength: ${medicine.strength}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
