package com.example.virtualcare.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.virtualcare.Activity.Doctors
import com.example.virtualcare.Models.CategoryModel
import com.example.virtualcare.R

class CategoryRecycler(val data:ArrayList<CategoryModel>, val context:Context): RecyclerView.Adapter<CategoryRecycler.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.img)
        private val prob: TextView = itemView.findViewById(R.id.problem)
        fun bind(data: CategoryModel) {
            prob.text = data.name
            image.setImageResource(data.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.docview, parent, false)
        return ViewHolder(view)    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            val intent=Intent(context, Doctors::class.java)
            intent.putExtra("type",data.name)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }
    }
}