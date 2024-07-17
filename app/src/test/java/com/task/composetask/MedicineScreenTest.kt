package com.task.composetask

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.task.composetask.ui.theme.feature.homescreen.MedicineItem
import com.task.composetask.ui.theme.feature.homescreen.MedicineViewModel
import com.task.composetask.ui.theme.feature.homescreen.MedicinesScreen
import com.task.composetask.utils.NavScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MedicinesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var viewModel: MedicineViewModel

    @Mock
    private lateinit var navController: NavController

    private val medicinesLiveData = MutableLiveData<List<MedicineItem>>()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        `when`(viewModel.medicines).thenReturn(medicinesLiveData)
    }

    @Test
    fun testMedicinesScreenDisplaysCorrectly() {
        composeTestRule.setContent {
            MedicinesScreen(username = "testuser", viewModel = viewModel, navController = navController)
        }

        composeTestRule.onNodeWithText("Welcome, testuser!").assertExists()
        // You can add more assertions to check for the presence of medicine items if they are available
    }

    @Test
    fun testMedicineItemClickNavigatesToDetail() {
        val medicineItem = MedicineItem(name = "Aspirin", dose = "500mg", strength = "Strong")
        medicinesLiveData.postValue(listOf(medicineItem))

        composeTestRule.setContent {
            MedicinesScreen(username = "testuser", viewModel = viewModel, navController = navController)
        }

        composeTestRule.onNodeWithText("Aspirin").performClick()

        verify(navController).navigate(NavScreen.MedicineDetailScreen.route)
        verify(navController.currentBackStackEntry?.savedStateHandle)?.set("medicine", medicineItem)
    }
}