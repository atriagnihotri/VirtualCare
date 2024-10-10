package com.example.virtualcare.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.virtualcare.R

class PatientDetail : AppCompatActivity() {
    lateinit var name:EditText
    lateinit var phone:EditText
    lateinit var next:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_detail)
        name=findViewById(R.id.Name)
        phone=findViewById(R.id.Phone)
        next=findViewById(R.id.cont)

        next.setOnClickListener {
            val names=name.text.toString()
            val phones=phone.text.toString()

            val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("name",names)
            editor.putString("phone",phones)
            editor.putBoolean("login",true)
            editor.apply()
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))

        }


    }
}