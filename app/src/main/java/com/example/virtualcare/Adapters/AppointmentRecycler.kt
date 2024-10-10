package com.example.virtualcare.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.virtualcare.Models.AppointmentModel
import com.example.virtualcare.R

class AppointmentRecycler(val data:List<AppointmentModel>, val context: Context): RecyclerView.Adapter<AppointmentRecycler.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val docname: TextView = itemView.findViewById(R.id.name)
        private val problem: TextView = itemView.findViewById(R.id.problem)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val Time: TextView = itemView.findViewById(R.id.Time)

        fun bind(data: AppointmentModel) {
            docname.text = "Dr. "+data.docname
            problem.text = data.problem
            date.text = data.scdDate
            Time.text = data.time


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.appointment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }
}