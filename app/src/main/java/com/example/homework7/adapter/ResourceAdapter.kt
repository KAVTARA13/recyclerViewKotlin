package com.example.homework7.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework7.R
import com.example.homework7.api.dto.Resource

class ResourceAdapter(private val context: Context, private val resource: List<Resource>, val listener: (Resource) -> Unit) : RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder>(){

    class ResourceViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val colorView = itemView.findViewById<Button>(R.id.colorView)
        private val nameName = itemView.findViewById<TextView>(R.id.nameName)
        private val year = itemView.findViewById<TextView>(R.id.year)
        private val color = itemView.findViewById<TextView>(R.id.color)
        private val pantoneValue = itemView.findViewById<TextView>(R.id.pantone_value)

        fun onBind(resource: Resource, listener: (Resource) -> Unit){
            nameName.text = resource.name
            year.text = resource.year.toString()
            color.text = resource.color
            pantoneValue.text = resource.pantoneValue
            colorView.setOnClickListener{listener(resource)}
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.resource,parent,false)
        return ResourceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResourceAdapter.ResourceViewHolder, position: Int) {
        holder.onBind(resource[position],listener)
    }

    override fun getItemCount(): Int {
        return resource.size
    }
}