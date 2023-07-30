package com.example.rentocar

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentocar.adapter.carlistadapter
import com.example.rentocar.adapter.luxurycarlistadapter
import com.example.rentocar.databinding.ActivityFirstPageBinding
import com.example.rentocar.dataclass.carlist
import com.example.rentocar.dataclass.luxurycarlist
import com.google.android.play.core.integrity.p
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class firstPage : AppCompatActivity() {

    lateinit var binding: ActivityFirstPageBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerView2: RecyclerView
    lateinit var list: ArrayList<carlist>
    lateinit var list2: ArrayList<luxurycarlist>

    var p: ProgressDialog? = null
    lateinit var firebaserefrence: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rec.setOnClickListener {
            binding.rec.setBackgroundResource(R.drawable.back3)
            binding.recommended.setTextColor(resources.getColor(R.color.black))
            binding.nearyou.setTextColor(resources.getColor(R.color.white))
            binding.near.setBackgroundResource(R.drawable.back2)

        }
        binding.near.setOnClickListener {
            binding.near.setBackgroundResource(R.drawable.back3)
            binding.recommended.setTextColor(resources.getColor(R.color.white))
            binding.nearyou.setTextColor(resources.getColor(R.color.black))
            binding.rec.setBackgroundResource(R.drawable.back2)

        }
        loaddata()
        loaddata2()
    }

    private fun loaddata2() {


        recyclerView2 = findViewById(com.example.rentocar.R.id.rv2)!!
        recyclerView2.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        list2 = arrayListOf()
        firebaserefrence = FirebaseDatabase.getInstance().getReference("Luxury-Car-Details")
        firebaserefrence.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val photo = i.getValue(luxurycarlist::class.java)
                        list2.add(photo!!)
                    }

                    recyclerView2.adapter = luxurycarlistadapter(this@firstPage, list2)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@firstPage, error.toString(), Toast.LENGTH_SHORT)
                    .show()
            }


        })

    }


    private fun loaddata() {

        recyclerView = findViewById(com.example.rentocar.R.id.rv1)!!
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        list = arrayListOf()
        firebaserefrence = FirebaseDatabase.getInstance().getReference("Car-Details")
        firebaserefrence.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val photo = i.getValue(carlist::class.java)
                        list.add(photo!!)
                    }

                    recyclerView.adapter = carlistadapter(this@firstPage, list)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@firstPage, error.toString(), Toast.LENGTH_SHORT)
                    .show()
            }


        })

    }
}