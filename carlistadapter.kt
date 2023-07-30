package com.example.rentocar.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.rentocar.databinding.Layout1Binding
import com.example.rentocar.dataclass.carlist
import com.example.rentocar.detailsOfCar

class carlistadapter(var context :  Context , var list : ArrayList<carlist>) : Adapter<carlistadapter.vie>() {
    inner class vie(view : View) : ViewHolder(view){
        var b : Layout1Binding = Layout1Binding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vie {
        return vie(LayoutInflater.from(context).inflate(com.example.rentocar.R.layout.layout1 , parent , false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: vie, position: Int) {
        var i = list[position]
        holder.b.locationtext.text = i.carname
        holder.b.text2.text = i.carmodel
        holder.b.range.text = i.range
        holder.b.nhp.text = i.power
        holder.b.mph.text = i.miles
        holder.b.seco.text = i.speed
      Glide.with(context).load(i.uri).into(holder.b.carimage)

        holder.itemView.setOnClickListener {
            val intent =  Intent(context , detailsOfCar::class.java)
            intent.putExtra("carname" , i.carname)
            context.startActivity(intent)
        }
    }


}