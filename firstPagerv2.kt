package com.example.adminrentocar

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.adminrentocar.databinding.ActivityFirstPagerv2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class firstPagerv2 : AppCompatActivity() {
    lateinit var binding : ActivityFirstPagerv2Binding
    private var id = 123
    lateinit var firebaseref : DatabaseReference
    private var firbaseStorage =  Firebase.storage
    var uri : Uri? = null
    private  var pro : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstPagerv2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val im = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                binding.imageView.setImageURI(it)
                uri = it!!
            })
        binding.storagebtn.setOnClickListener {
            im.launch("image/*")
        }

        binding.camerabtn.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, id)
        }
        binding.uploadbtn.setOnClickListener {
            uploaddata()
        }
    }


private fun uploaddata() {
    progressbar()

    firbaseStorage.getReference("Luxury-Car-Details").child(binding.carname.text.toString())
        .putFile(uri!!).addOnSuccessListener {task ->
            task.metadata?.reference?.downloadUrl
                ?.addOnSuccessListener {
                    val map = hashMapOf(
                        "uri" to it.toString(),
                        "id" to binding.id.text.toString(),
                        "carname" to binding.carname.text.toString(),
                        "carmodel" to binding.model.text.toString(),

                    )
                    firebaseref =
                        FirebaseDatabase.getInstance().getReference("Luxury-Car-Details")
                    firebaseref.child(binding.carname.text.toString()).setValue(map)
                        .addOnSuccessListener {
                            binding.imageView.setImageResource(com.example.adminrentocar.R.drawable.profile)
                            binding.carname.text.clear()
                            binding.model.text.clear()

                            pro?.dismiss()
                            Toast.makeText(this, "uploded", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { task ->
                            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                        }

                }
        }


}
private fun progressbar(){
    pro =  ProgressDialog(this)
    pro?.setCancelable(false)
    pro?.setMessage("Please wait..")
    pro?.show()
}



@Deprecated("Deprecated in Java")
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if(requestCode == id){
        val photo =  data?.extras?.get("data")  as Bitmap
        val i =  MediaStore.Images.Media.insertImage(
            contentResolver,
            photo,
            "f",
            "ff"
        )
        uri =  Uri.parse(i)
        binding.imageView.setImageURI(uri)
    }

}
}