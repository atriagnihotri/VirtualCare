package com.example.virtualcare.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.virtualcare.Activity.DetailView
import com.example.virtualcare.Activity.Doctors
import com.example.virtualcare.Models.DoctorModel
import com.example.virtualcare.R

class DoctorRecycler(val data:List<DoctorModel>, val context:Context): RecyclerView.Adapter<DoctorRecycler.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val names: TextView = itemView.findViewById(R.id.name)
        private val type: TextView = itemView.findViewById(R.id.type)
        private val experience: TextView = itemView.findViewById(R.id.experience)

        fun bind(data: DoctorModel) {
            names.text = "Dr."+data.name
            type.text = data.type+" Specialist"
            experience.text = data.Experience+" Years Experience"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doctorview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            val intent= Intent(context, DetailView::class.java)
            intent.putExtra("name",data.name)
            intent.putExtra("type",data.type)
            intent.putExtra("experience",data.Experience)
            intent.putExtra("degree",data.degree)
            intent.putExtra("phone",data.phone)
            intent.putExtra("connectId",data.connectId)
            intent.putExtra("timeopen",data.timeopen)
            intent.putExtra("timeclose",data.timeclose)
            intent.putExtra("workingdays",data.workingdays)
            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }
    }
}