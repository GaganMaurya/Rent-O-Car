package com.example.rentocar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.rentocar.databinding.Layout1Binding
import com.example.rentocar.databinding.Layout2Binding
import com.example.rentocar.dataclass.carlist
import com.example.rentocar.dataclass.luxurycarlist

class luxurycarlistadapter(var context :  Context, var list : ArrayList<luxurycarlist>) : Adapter<luxurycarlistadapter.vie>() {
    inner class vie(view : View) : ViewHolder(view){
        var b : Layout2Binding = Layout2Binding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vie {
        return vie(LayoutInflater.from(context).inflate(com.example.rentocar.R.layout.layout2 , parent , false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: vie, position: Int) {
        var i = list[position]
        holder.b.locationtext.text = i.carname
        holder.b.text2.text = i.carmodel
      Glide.with(context).load(i.uri).into(holder.b.carimage)
    }


}