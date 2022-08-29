package com.example.testing

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.testing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReset.setOnClickListener { reset() }
        binding.submitBtn.setOnClickListener { submit() }
        binding.websiteBtn.setOnClickListener { website() }
    }

    private fun reset(){
        binding.edtName.text.clear()
        binding.spnCampus.setSelection(0)
        binding.edtCGPA.text.clear()
        binding.edtName.requestFocus()
    }

    private fun submit(){
        //input (string, int, double)
        val name = binding.edtName.text.toString().trim()
        val campus = binding.spnCampus.selectedItem as String
        val cgpa = binding.edtCGPA.text.toString().toDoubleOrNull() ?: -1.0

        if (name == ""){
            toast("invalid name")
            return
        }
        if (cgpa < 0.0 || cgpa > 4.0){
            toast("invalid cgpa")
            return
        }
        //calculation
        var award = ""
        var prize = 0.00

        when{
            cgpa >= 3.75 ->{ award = "Distinction"; prize = 100.00 }
            cgpa >= 2.75 ->{ award = "Award"; prize = 50.00 }
            cgpa >= 2.00 ->{ award = "Pass"; prize = 0.00 }
            else -> { award = "Fail"; prize = 0.00 }
        }
        //data passing - explicit intent
        val intent = Intent(this, SUbActivity::class.java)
            .putExtra("name", name)
            .putExtra("campus", campus)
            .putExtra("cgpa", cgpa)
            .putExtra("prize",prize)
            .putExtra("award",award)

        startActivity(intent)
    }

    private fun website(){
        val uri = Uri.parse("https://www.tarc.edu.my")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun toast(s: String){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}