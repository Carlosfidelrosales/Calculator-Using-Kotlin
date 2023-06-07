package com.main.calculator;
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.view.View
import android.widget.TextView
import android.os.Bundle

class CalculatorActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var txtInput: TextView
    private lateinit var txtDisplay: TextView

    private var currentNumber = StringBuilder()
    private var currentOperator: String? = null
    private var operand1: Double? = null
    private var operand2: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        txtInput = findViewById(R.id.txtInput)
        txtDisplay = findViewById(R.id.txtDisplay)

        val numberButtons = arrayOf(
            findViewById<Button>(R.id.btn0),
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6),
            findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8),
            findViewById<Button>(R.id.btn9)
        )
        val operatorButtons = arrayOf(
            findViewById<Button>(R.id.btnAdd),
            findViewById<Button>(R.id.btnSubtract),
            findViewById<Button>(R.id.btnMultiply),
            findViewById<Button>(R.id.btnDivide)
        )

        for (button in numberButtons) {
            button.setOnClickListener(this)
        }

        for (button in operatorButtons) {
            button.setOnClickListener(this)
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener(this)
        findViewById<Button>(R.id.btnClear).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9 -> {
                val number = (view as Button).text.toString()
                currentNumber.append(number)
                txtDisplay.text = currentNumber.toString()
            }
            R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide -> {
                if (currentNumber.isNotEmpty()) {
                    currentOperator = (view as Button).text.toString()
                    operand1 = currentNumber.toString().toDouble()
                    currentNumber.clear()
                    txtInput.text = operand1.toString() + " " + currentOperator
                    txtDisplay.text = ""
                }
            }
            R.id.btnEquals -> {
                if (currentOperator != null && currentNumber.isNotEmpty()) {
                    operand2 = currentNumber.toString().toDouble()
                    val result = calculateResult()
                    txtDisplay.text = result.toString()
                    txtInput.text =
                        operand1.toString() + " " + currentOperator + " " + operand2.toString() + " ="
                    currentNumber.clear()
                    currentNumber.append(result)
                    currentOperator = null
                }
            }
            R.id.btnClear -> {
                currentNumber.clear()
                currentOperator = null
                operand1 = null
                operand2 = null
                txtDisplay.text = ""
                txtInput.text = ""
            }
        }
    }

    private fun calculateResult(): Double {
        val num1 = operand1 ?: 0.0
        val num2 = operand2 ?: 0.0
        return when (currentOperator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "x" -> num1 * num2
            "/" -> num1 / num2
            else -> 0.0
        }
    }
}
