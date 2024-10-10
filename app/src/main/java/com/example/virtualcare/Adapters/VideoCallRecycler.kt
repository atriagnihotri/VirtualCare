package com.example.virtualcare.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import android.Manifest
import com.example.virtualcare.Activity.VideoCallView
import com.example.virtualcare.Models.DoctorModel
import com.example.virtualcare.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class VideoCallRecycler(val data: List<DoctorModel>, val context: Context): RecyclerView.Adapter<VideoCallRecycler.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val names: TextView = itemView.findViewById(R.id.name)
        private val type: TextView = itemView.findViewById(R.id.type)
        private val experience: TextView = itemView.findViewById(R.id.experience)
        private val call: TextView = itemView.findViewById(R.id.call)
        private val videocall: TextView = itemView.findViewById(R.id.video)

        fun bind(data: DoctorModel, context: Context) {
            names.text = "Dr." + data.name
            type.text = data.type + " Specialist"
            experience.text = data.Experience + " Years Experience"

            call.setOnClickListener {
                val phoneNumber = "tel:" + data.phone
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse(phoneNumber)
                context.startActivity(dialIntent)
            }

            videocall.setOnClickListener {
                checkPermission(context,data.connectId)
            }
        }

        private fun checkPermission(context: Context,connect:String) {
            Dexter.withContext(context)
                .withPermissions(
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO
                ).withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        if (report.areAllPermissionsGranted()) {
                            val intent = Intent(context, VideoCallView::class.java)
                            intent.putExtra("connectId",connect)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            context.startActivity(intent)  // Do not use applicationContext
                        } else {
                            Toast.makeText(context, "Permissions are required to record video and audio", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: List<com.karumi.dexter.listener.PermissionRequest>,
                        token: PermissionToken
                    ) {
                        token.continuePermissionRequest()
                    }
                }).check()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.videocallview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doctor = data[position]
        holder.bind(doctor, context)
    }
}
