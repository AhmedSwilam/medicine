package com.task.composetask.model.response

import com.google.gson.annotations.SerializedName

data class MedicineResponse(
    @SerializedName("problems") val problems: List<Problem>
)

data class Problem(
    @SerializedName("Diabetes") val diabetes: List<Diabetes>? = null,
)

data class Diabetes(
    @SerializedName("medications") val medications: List<Medication>,
    @SerializedName("labs") val labs: List<Lab>
)


data class Medication(
    @SerializedName("medicationsClasses") val medicationsClasses: List<MedicationClass>
)

data class MedicationClass(
    @SerializedName("className") val className: List<ClassName>? = null,
    @SerializedName("className2") val className2: List<ClassName2>? = null
)

data class ClassName(
    @SerializedName("associatedDrug") val associatedDrug: List<AssociatedDrug>,
    @SerializedName("associatedDrug#2") val associatedDrug2: List<AssociatedDrug2>
)

data class ClassName2(
    @SerializedName("associatedDrug") val associatedDrug: List<AssociatedDrug>,
    @SerializedName("associatedDrug#2") val associatedDrug2: List<AssociatedDrug2>
)

data class AssociatedDrug(
    @SerializedName("name") val name: String,
    @SerializedName("dose") val dose: String,
    @SerializedName("strength") val strength: String
)

data class AssociatedDrug2(
    @SerializedName("name") val name: String,
    @SerializedName("dose") val dose: String,
    @SerializedName("strength") val strength: String
)

data class Lab(
    @SerializedName("missing_field") val missingField: String
)
