package com.task.composetask.ui.theme.feature.medicinescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.task.composetask.ui.theme.feature.homescreen.MedicineItem

@Composable
fun MedicineDetailScreen(medicine: MedicineItem, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Name: ${medicine.name}", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Dose: ${medicine.dose}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Strength: ${medicine.strength}", style = MaterialTheme.typography.bodySmall)
    }
}
