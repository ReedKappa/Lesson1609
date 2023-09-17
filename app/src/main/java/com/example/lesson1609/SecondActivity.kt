package com.example.lesson1609

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val userAnswer = findViewById<TextView>(R.id.userAnswer)
        val machineAnswer = findViewById<TextView>(R.id.machineAnswer)
        val result = findViewById<TextView>(R.id.result)

        val bundle: Bundle? = intent.getExtras()
        val DATA = bundle?.getInt("DATA")
        DATA?.let {userAnswer.text = Choice.values()[DATA].toString() }

        val machine = chooseRandom()
        machineAnswer.text = machine.toString()

        if (DATA != null) {
            result.text = compareAnswers(Choice.values()[DATA].ordinal, machine.ordinal)
        }
    }

    private fun chooseRandom() : Choice {
        return Choice.values()[Random.nextInt(0, 3)]
    }

    private fun compareAnswers(user: Int, machine: Int): String {

        if (user == machine) {
            return "Ничья!"
        }

        if (machine - user > 0) {
            if ((machine - user) / 2 == 0){
                return "Вы победили!"
            }
            return "Вы проиграли!"
        } else {
            if ((user - machine) / 2 == 0) {
                return "Вы проиграли!"
            }
            return "Вы победили!"
        }
    }
}