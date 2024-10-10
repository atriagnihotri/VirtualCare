package com.example.virtualcare.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.virtualcare.Adapters.DoctorRecycler
import com.example.virtualcare.Adapters.VideoCallRecycler
import com.example.virtualcare.Models.DoctorModel
import com.example.virtualcare.NetworkCall.ApiInterface
import com.example.virtualcare.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstantCall : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    lateinit var adapter:VideoCallRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instant_call)

        recycler=findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(applicationContext).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        adapter = VideoCallRecycler(emptyList(), applicationContext)
        recycler.adapter = adapter


        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.43.249:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofit.getAllDoctor()
        retrofitData.enqueue(
            object : Callback<List<DoctorModel>?> {
                override fun onResponse(
                    call: Call<List<DoctorModel>?>,
                    response: Response<List<DoctorModel>?>
                ) {
                    val dataResponse = response.body()
                    dataResponse?.let {
                        adapter = VideoCallRecycler(it, applicationContext)
                        recycler.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<List<DoctorModel>?>, t: Throwable) {
                    val error = t.message
                    Toast.makeText(applicationContext, "Error: $error", Toast.LENGTH_SHORT).show()
                }
            })


    }
}