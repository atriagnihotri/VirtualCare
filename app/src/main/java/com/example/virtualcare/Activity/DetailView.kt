package com.example.virtualcare.Activity

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.virtualcare.Adapters.DoctorRecycler
import com.example.virtualcare.Models.DoctorModel
import com.example.virtualcare.Models.ScheduleModel
import com.example.virtualcare.NetworkCall.ApiInterface
import com.example.virtualcare.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailView : AppCompatActivity() {
    lateinit var names:TextView
    lateinit var typ:TextView
    lateinit var typs:TextView
    lateinit var exp:TextView
    lateinit var deg:TextView
    lateinit var open:TextView
    lateinit var close:TextView
    lateinit var work:TextView
    lateinit var tely:TextView
    lateinit var call:Button
    lateinit var schedule:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        names=findViewById(R.id.name)
        typ=findViewById(R.id.type)
        typs=findViewById(R.id.type)
        exp=findViewById(R.id.experience)
        deg=findViewById(R.id.degree)
        open=findViewById(R.id.opening)
        close=findViewById(R.id.closing)
        work=findViewById(R.id.days)
        call=findViewById(R.id.call)
        schedule=findViewById(R.id.schedule)
        tely=findViewById(R.id.phone)


        val name=intent.getStringExtra("name")
        val type=intent.getStringExtra("type")
        val experience=intent.getStringExtra("experience")
        val degree=intent.getStringExtra("degree")
        val connectId=intent.getStringExtra("connectId")
        val timeopen=intent.getStringExtra("timeopen")
        val timeclose=intent.getStringExtra("timeclose")
        val workingdays=intent.getStringExtra("workingdays")
        val phone=intent.getStringExtra("phone")

        names.text="Dr. "+name.toString()
        typ.text=type.toString()+" Specialist"
        exp.text=experience.toString()+" Years Experience"
        deg.text=degree
        open.text=timeopen.toString()+" AM"
        close.text=timeclose.toString()+" PM"
        work.text=workingdays.toString()+" Days a week"
        tely.text="+91"+phone.toString()
        typs.text=type.toString()



        call.setOnClickListener {
            val phoneNumber = "tel:"+phone.toString()
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse(phoneNumber)
            startActivity(dialIntent)

        }
        schedule.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Schedule Apoointment")

            val customLayout: View = layoutInflater.inflate(R.layout.custom_layout, null)
            builder.setView(customLayout)

            builder.setPositiveButton("Schedule") { dialog: DialogInterface?, which: Int ->
                val problems = customLayout.findViewById<EditText>(R.id.problem)
                val dates = customLayout.findViewById<EditText>(R.id.date)
                val times = customLayout.findViewById<EditText>(R.id.time)
                val problem=problems.text
                val date=dates.text
                val time=times.text
                val docname=names.text
                val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                val name = sharedPref.getString("name", "Default")
                val phone = sharedPref.getString("phone", "phone")
                val conId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
                startActivity(Intent(this,Appointment::class.java))

                val scheduleModel=ScheduleModel(name.toString(),conId,docname.toString(),phone.toString(),problem.toString(),date.toString(),time.toString())
                PostApoointment(scheduleModel)
            }
            builder.setNegativeButton("Cancel") { dialog: DialogInterface?, which: Int ->
            }
            val dialog = builder.create()
            dialog.show()        }



    }



    fun PostApoointment( schedulemodel:ScheduleModel){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.43.249:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofit.Schedule(schedulemodel)
        retrofitData.enqueue(
            object : Callback<ScheduleModel?> {
                override fun onResponse(
                    call: Call<ScheduleModel?>,
                    response: Response<ScheduleModel?>
                ) {
                    val dataResponse = response.body()
                    dataResponse?.let {
                        Toast.makeText(applicationContext, "Scheduled Successfully", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ScheduleModel?>, t: Throwable) {
                    val error = t.message
                    Toast.makeText(applicationContext, "Error: $error", Toast.LENGTH_SHORT).show()
                }
            })
    }
}