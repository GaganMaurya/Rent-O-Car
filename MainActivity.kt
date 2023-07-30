package com.example.adminrentocar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adminrentocar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.first.setOnClickListener {
            startActivity(Intent(this , firstPagerv1::class.java))
        }
        b.second.setOnClickListener {
            startActivity(Intent(this , firstPagerv2::class.java))
        }
        b.third.setOnClickListener {
            startActivity(Intent(this , images::class.java))
        }

    }
}