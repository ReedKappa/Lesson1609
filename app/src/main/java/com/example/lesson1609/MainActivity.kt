package com.example.lesson1609

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var view: TextView
    var firstNumber = 0
    var symbol = ""
    val operationsMap = mapOf<Operations, String>(
        Operations.Plus to "+",
        Operations.Minus to "-",
        Operations.Multiply to "*",
        Operations.Divide to "/",
        Operations.Equals to "=",
        Operations.Clear to "Clear"
    )

    val numberMap = mapOf<Numbers, String>(
        Numbers.Zero to "0",
        Numbers.One to "1",
        Numbers.Two to "2",
        Numbers.Three to "3",
        Numbers.Four to "4",
        Numbers.Five to "5",
        Numbers.Six to "6",
        Numbers.Seven to "7",
        Numbers.Eight to "8",
        Numbers.Nine to "9"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.view = findViewById<TextView>(R.id.view)
        val one = findViewById<Button>(R.id.one)
        val two = findViewById<Button>(R.id.two)
        val three = findViewById<Button>(R.id.three)
        val four = findViewById<Button>(R.id.four)
        val five = findViewById<Button>(R.id.five)
        val six = findViewById<Button>(R.id.six)
        val seven = findViewById<Button>(R.id.seven)
        val eight = findViewById<Button>(R.id.eight)
        val nine = findViewById<Button>(R.id.nine)
        val plus = findViewById<Button>(R.id.plus)
        val minus = findViewById<Button>(R.id.minus)
        val multiply = findViewById<Button>(R.id.multiply)
        val divide = findViewById<Button>(R.id.divide)
        val equal = findViewById<Button>(R.id.equal)
        val delete = findViewById<Button>(R.id.delete)
        val clear = findViewById<Button>(R.id.clear)


        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)
        plus.setOnClickListener(this)
        minus.setOnClickListener(this)
        multiply.setOnClickListener(this)
        divide.setOnClickListener(this)
        equal.setOnClickListener(this)
        delete.setOnClickListener(this)
        view.setOnClickListener(this)
        clear.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.one -> Calculations(number = Numbers.One)
            R.id.two -> Calculations(number = Numbers.Two)
            R.id.three -> Calculations(number = Numbers.Three)
            R.id.four -> Calculations(number = Numbers.Four)
            R.id.five -> Calculations(number = Numbers.Five)
            R.id.six -> Calculations(number = Numbers.Six)
            R.id.seven -> Calculations(number = Numbers.Seven)
            R.id.eight -> Calculations(number = Numbers.Eight)
            R.id.nine -> Calculations(number = Numbers.Nine)
            R.id.plus -> Calculations(operation = Operations.Plus)
            R.id.minus -> Calculations(operation = Operations.Minus)
            R.id.multiply -> Calculations(operation = Operations.Multiply)
            R.id.divide -> Calculations(operation = Operations.Divide)
            R.id.delete -> Calculations(operation = Operations.Delete)
            R.id.equal -> Calculations(operation = Operations.Equals)
            R.id.clear -> Calculations(operation = Operations.Clear)
        }
    }

    fun Calculations(number: Numbers? = null, operation: Operations? = null) {
        if (number != null) {
            if (view.text.contains("Введите числа")) {
                view.text = ""
            }
            view.text = view.text.toString() + numberMap.get(number)
        } else {
            when (operation) {
                Operations.Plus -> changeNumber(view.text.toString(), Operations.Plus)
                Operations.Minus -> changeNumber(view.text.toString(), Operations.Minus)
                Operations.Multiply -> changeNumber(view.text.toString(), Operations.Multiply)
                Operations.Divide -> changeNumber(view.text.toString(), Operations.Divide)
                Operations.Delete -> deleteNumber()
                Operations.Equals -> {
                    symbol = view.text.toString().dropLast(view.text.length - 1)
                    getAnswer(view.text.toString().drop(1), findMap(operationsMap, symbol))
                }
                Operations.Clear -> clear()

                null -> return
            }
        }
    }

    private fun clear() {
        view.text = ""
        firstNumber = 0
    }

    private fun getAnswer(number: String, operation: Operations) {
        when (operation) {
            Operations.Plus -> view.text = (firstNumber + number.toInt()).toString()
            Operations.Minus -> view.text = (firstNumber - number.toInt()).toString()
            Operations.Multiply -> view.text = (firstNumber * number.toInt()).toString()
            Operations.Divide -> view.text = (firstNumber / number.toInt()).toString()

            else -> {
                return
            }
        }

    }

    fun changeNumber(number: String, operation: Operations) {
        firstNumber = number.toInt()
        view.text = operationsMap[operation]
    }

    fun deleteNumber() {
        if (view.text.isEmpty()) {
            firstNumber = 0
            return
        }
        view.text = view.text.dropLast(1)
    }

    fun findMap(hashMap: Map<Operations, String>, operationSymbol: String) : Operations {
        return hashMap.filter { operationSymbol == it.value }.keys.first()
    }
}

enum class Numbers {
    Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine
}

enum class Operations {
    Plus, Minus, Multiply, Divide, Delete, Equals, Clear
}