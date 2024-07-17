package com.task.composetask.ui.theme.feature.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.composetask.model.data.MedicineRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val medicineRemoteSource: MedicineRemoteSource
) : ViewModel() {

    private val _medicines = MutableLiveData<List<MedicineItem>>()
    val medicines: LiveData<List<MedicineItem>> = _medicines

    init {
        fetchMedicines()
    }

    private fun fetchMedicines() = viewModelScope.launch {
        val medicineList = medicineRemoteSource.getMedicine().flatMap { diabetes ->
            diabetes.medications.flatMap { medicationClass ->
                medicationClass.medicationsClasses.flatMap { className ->
                    val drugs1 = className.className?.flatMap { it.associatedDrug.map { drug ->
                        MedicineItem(drug.name, drug.dose, drug.strength)
                    } } ?: emptyList()

                    val drugs2 = className.className2?.flatMap { it.associatedDrug.map { drug ->
                        MedicineItem(drug.name, drug.dose, drug.strength)
                    } } ?: emptyList()

                    drugs1 + drugs2
                }
            }
        }
        _medicines.postValue(medicineList)
    }
}
