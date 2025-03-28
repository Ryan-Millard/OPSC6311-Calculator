package com.ryan.opsc6311calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultDisplay: TextView
    private var currentInput: String = ""
    private var operator: String? = null
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultDisplay = findViewById(R.id.resultDisplay)

        val buttons = listOf(
            findViewById<Button>(R.id.button_1), findViewById<Button>(R.id.button_2),
            findViewById<Button>(R.id.button_3), findViewById<Button>(R.id.button_4),
            findViewById<Button>(R.id.button_5), findViewById<Button>(R.id.button_6),
            findViewById<Button>(R.id.button_7), findViewById<Button>(R.id.button_8),
            findViewById<Button>(R.id.button_9), findViewById<Button>(R.id.button_0),
            findViewById<Button>(R.id.button_add), findViewById<Button>(R.id.button_sub),
            findViewById<Button>(R.id.button_mul), findViewById<Button>(R.id.button_div),
            findViewById<Button>(R.id.button_clear), findViewById<Button>(R.id.button_eq)
        )

        buttons.forEach { button ->
            button.setOnClickListener { onButtonClick(it) }
        }
    }

    private fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()

        when (buttonText) {
            "C" -> clear()
            "=" -> calculate()
            "+" -> setOperator("+")
            "-" -> setOperator("-")
            "├ù" -> setOperator("*")
            "├╖" -> setOperator("/")
            else -> appendInput(buttonText)
        }
    }

    private fun appendInput(input: String) {
        currentInput += input
        resultDisplay.text = buildString {
            append(resultDisplay.text.toString())
            append(currentInput)
        }
    }

    private fun clear() {
        currentInput = ""
        operand1 = 0.0
        operand2 = 0.0
        operator = null
        resultDisplay.text = ""
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            operand1 = currentInput.toDouble()
            currentInput = ""
            operator = op
            resultDisplay.text = buildString {
                append(resultDisplay.text.toString())
                append(op)
            }
        }
    }

    private fun calculate() {
        if (currentInput.isNotEmpty() && operator != null) {
            operand2 = currentInput.toDouble()
            val result = when (operator) {
                "+" -> operand1 + operand2
                "-" -> operand1 - operand2
                "*" -> operand1 * operand2
                "/" -> if (operand2 != 0.0) operand1 / operand2 else "Error"
                else -> 0.0
            }
            resultDisplay.text = result.toString()
            currentInput = result.toString()
            operator = null
        }
    }
}
