package com.example.rentocar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rentocar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding :  ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btn.setOnClickListener {

            val intent = Intent(this ,  firstPage::class.java)
            startActivity(intent)
        }
    }
}