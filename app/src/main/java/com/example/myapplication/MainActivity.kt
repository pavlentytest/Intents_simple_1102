package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {


    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if(result.resultCode == 200) {
            val str = result.data?.getStringExtra("key2")
            Toast.makeText(applicationContext,str, Toast.LENGTH_LONG).show()
        }
    }
    private lateinit var btn1: Button
    private lateinit var btn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = "https://mirea.ru";
        btn1 = findViewById(R.id.button)
        btn1.setOnClickListener{
            // неявное намерение
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
        }

        btn2 = findViewById(R.id.button2)
        btn2.setOnClickListener {
            // явное намерение
            val intent = Intent(applicationContext, MainActivity2::class.java)
            intent.putExtra("key", "Hello from MainActivity!")
           // startActivity(intent)
            startForResult.launch(intent)
        }

    }


}