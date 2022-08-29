package com.example.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testing.databinding.ActivitySubBinding

class SUbActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnClose.setOnClickListener { finish() }

        //data
        val name =intent.getStringExtra("name") ?:""
        val campus = intent.getStringExtra("campus") ?:""
        val cgpa = intent.getDoubleExtra("cgpa", 0.0)
        val award = intent.getStringExtra("award") ?:""
        val prize = intent.getDoubleExtra("prize", 0.0)

        binding.txtOutput.text = """
            Name   : $name
            Campus : $campus
            CGPA   : $cgpa
            Award  : $award
            Prize  : RM ${"%.2f".format(prize)}
            
        """.trimIndent()

    }
}