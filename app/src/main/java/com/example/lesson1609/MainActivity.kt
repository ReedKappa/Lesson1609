package com.example.lesson1609

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rockButton = findViewById<Button>(R.id.rock)
        val paperButton = findViewById<Button>(R.id.paper)
        val scissorsButton = findViewById<Button>(R.id.scissors)


        rockButton.setOnClickListener(this)
        paperButton.setOnClickListener(this)
        scissorsButton.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.paper -> {
                sendResult(Choice.Paper)
            }
            R.id.scissors -> {
                sendResult(Choice.Scissors)
            }
            R.id.rock -> {
                sendResult(Choice.Rock)
            }
        }

    }

    private fun sendResult(choice: Choice) {
        val intent = Intent(this, SecondActivity::class.java)
        val bundle = Bundle().apply {
            putInt("DATA", choice.ordinal)
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }
}

enum class Choice {
    Rock, Scissors, Paper
}