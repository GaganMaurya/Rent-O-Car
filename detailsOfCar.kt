package com.example.rentocar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.rentocar.databinding.ActivityDetailsOfCarBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.R
import com.google.firebase.database.ValueEventListener

class detailsOfCar : AppCompatActivity() {
    var imagesliderlist = ArrayList<SlideModel>()

    lateinit var firebaserefrence: DatabaseReference
    lateinit var binding: ActivityDetailsOfCarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsOfCarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var carname =  intent.getStringExtra("carname")
        loadimageslider(carname.toString())



    }

    fun loadimageslider(s : String){
        firebaserefrence = FirebaseDatabase.getInstance().getReference("Car-Image-Slider").child(s)
        firebaserefrence.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {

                        val data = i.getValue(object :
                            GenericTypeIndicator<HashMap<String, Any>>() {})

                        val imageUrl = data?.get("uri") as? String

                        if (imageUrl != null) {
                            imagesliderlist.add(SlideModel(imageUrl))
                        }
                        val imgSlider = findViewById<ImageSlider>(com.example.rentocar.R.id.imageSlider)
                        imgSlider?.setImageList(imagesliderlist, ScaleTypes.FIT)

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@detailsOfCar, error.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }
    }
}