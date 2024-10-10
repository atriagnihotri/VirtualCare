package com.example.virtualcare.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.virtualcare.Adapters.AppointmentRecycler
import com.example.virtualcare.Adapters.VideoCallRecycler
import com.example.virtualcare.Models.AppointmentModel
import com.example.virtualcare.Models.DoctorModel
import com.example.virtualcare.NetworkCall.ApiInterface
import com.example.virtualcare.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Appointment : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    lateinit var adapter: AppointmentRecycler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)
        recycler=findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(applicationContext).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        adapter = AppointmentRecycler(emptyList(), applicationContext)
        recycler.adapter = adapter

        val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val name = sharedPref.getString("name", "Default")



        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.43.249:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofit.getAppointments(name.toString())
        retrofitData.enqueue(
            object : Callback<List<AppointmentModel>?> {
                override fun onResponse(
                    call: Call<List<AppointmentModel>?>,
                    response: Response<List<AppointmentModel>?>
                ) {
                    val dataResponse = response.body()
                    dataResponse?.let {
                        adapter = AppointmentRecycler(it, applicationContext)
                        recycler.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<List<AppointmentModel>?>, t: Throwable) {
                    val error = t.message
                    Toast.makeText(applicationContext, "Error: $error", Toast.LENGTH_SHORT).show()
                }
            })


    }
}