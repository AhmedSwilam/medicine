package com.task.composetask.model.data

import com.task.composetask.model.response.MedicineResponse
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicineApi  @Inject constructor(private val service: Service) {

    suspend fun getMedicine(): MedicineResponse = service.getMedicine()

    interface Service {

        @GET("incident")
        suspend fun getMedicine(): MedicineResponse

    }

    companion object {
        const val API_URL = "https://run.mocky.io/v3/2bf0a047-d3a3-492e-b153-ce45d28261ab/"
    }
}