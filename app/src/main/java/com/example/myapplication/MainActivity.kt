package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton : Button
    internal lateinit var timeTextView : TextView
    internal lateinit var counterTextView : TextView
    internal var counter = 0
    internal var time = 60


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.counterTextView)

        // Actualitzar o defini valor inicial del counterTextViu -> counterTextViu = conuter -> 0

        // TODO en algun moment haure d'executar incrementCounter

        tapMeButton.setOnClickListener{
            incrementCounter()
        }
        //Següent video:https://www.youtube.com/watch?v=Zt4nWQ_JTiM&list=PLyasg1A0hpk21ORaTE8r_mA8YbMAZI76e&index=7
        timeTextView.text = getString(R.string.timeText, time)
    }

    private fun incrementCounter(){
        counter += 1
        counterTextView.text = counter.toString()
    }
}