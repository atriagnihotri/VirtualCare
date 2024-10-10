package com.example.virtualcare.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.virtualcare.Adapters.CategoryRecycler
import com.example.virtualcare.Models.CategoryModel
import com.example.virtualcare.R

class MainActivity : AppCompatActivity() {
    lateinit var recycler:RecyclerView
    lateinit var arr:ArrayList<CategoryModel>
    lateinit var adapter: CategoryRecycler
    lateinit var appoint: ImageView
    lateinit var quickcall: ImageView
    lateinit var schedule: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler=findViewById(R.id.recycler)
        appoint=findViewById(R.id.appointment)
        quickcall=findViewById(R.id.quickcall)
        schedule=findViewById(R.id.schedule)
        arr= ArrayList()
        arr.add(CategoryModel(R.drawable.physician,"Physician"))
        arr.add(CategoryModel(R.drawable.surgeon,"Surgeon"))
        arr.add(CategoryModel(R.drawable.pimples,"Skin & Hair"))
        arr.add(CategoryModel(R.drawable.heart,"Heart"))
        arr.add(CategoryModel(R.drawable.eye,"Eye"))
        arr.add(CategoryModel(R.drawable.mental,"Mental"))
        arr.add(CategoryModel(R.drawable.ear,"ENT"))
        arr.add(CategoryModel(R.drawable.ayurveda,"Ayurveda"))
        arr.add(CategoryModel(R.drawable.floss,"Dental"))
        arr.add(CategoryModel(R.drawable.baby,"Baby"))
        arr.add(CategoryModel(R.drawable.women,"Women's Health"))
        recycler.layoutManager = GridLayoutManager(applicationContext, 3)
        adapter= CategoryRecycler(arr,applicationContext)
        recycler.adapter=adapter

        appoint.setOnClickListener {
            startActivity(Intent(this,AllDocs::class.java))
        }

        quickcall.setOnClickListener {
            startActivity(Intent(this,InstantCall::class.java))

        }
        schedule.setOnClickListener {
            startActivity(Intent(this,Appointment::class.java))
        }







    }
}