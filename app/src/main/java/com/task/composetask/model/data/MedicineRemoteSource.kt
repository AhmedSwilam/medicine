package com.task.composetask.model.data

import com.task.composetask.model.response.Diabetes
import com.task.composetask.model.response.MedicineResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicineRemoteSource @Inject constructor(private val medicineApi: MedicineApi) {

    private var cachedMedicine: List<Diabetes>? = null

    suspend fun getMedicine(): List<Diabetes> = withContext(Dispatchers.IO) {
        var localCache = cachedMedicine
        if (localCache == null) {
            localCache = medicineApi.getMedicine().mapIncidentsToItems()
            cachedMedicine = localCache
        }
        return@withContext localCache
    }

    private fun MedicineResponse.mapIncidentsToItems(): List<Diabetes> {
        return this.problems.flatMap { problem ->
            problem.diabetes ?: emptyList()
        }
    }

}