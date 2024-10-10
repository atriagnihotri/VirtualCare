package com.example.virtualcare.NetworkCall

import com.example.virtualcare.Models.AppointmentModel
import com.example.virtualcare.Models.DoctorModel
import com.example.virtualcare.Models.ScheduleModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @GET("/doctor/data")
    fun getSpecialisedDoctor(@Query("type") type: String): Call<List<DoctorModel>>

    @GET("/doctor/data")
    fun getAllDoctor(): Call<List<DoctorModel>>

    @POST("/appoint/patient")
    fun Schedule(@Body scheduleModel: ScheduleModel): Call<ScheduleModel>

    @GET("/appoint/patient")
    fun getAppointments(@Query("name") name: String): Call<List<AppointmentModel>>

}