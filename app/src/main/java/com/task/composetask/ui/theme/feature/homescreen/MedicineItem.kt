package com.task.composetask.ui.theme.feature.homescreen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MedicineItem(
    val name: String,
    val dose: String,
    val strength: String
) : Parcelable


