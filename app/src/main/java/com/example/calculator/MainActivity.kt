package com.example.calculator
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var isNewOp = true
    private var firstNumber = ""
    private var operation = ""
    private var comma = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun numberClick(view: View) {
        if (isNewOp)
            textViewOutput.text = ""
        isNewOp = false
        var buttonClick = textViewOutput.text.toString()
        val buttonSelect = view as Button
        when(buttonSelect.id) {
            button0.id -> buttonClick+="0"
            button1.id -> buttonClick+="1"
            button2.id -> buttonClick+="2"
            button3.id -> buttonClick+="3"
            button4.id -> buttonClick+="4"
            button5.id -> buttonClick+="5"
            button6.id -> buttonClick+="6"
            button7.id -> buttonClick+="7"
            button8.id -> buttonClick+="8"
            button9.id -> buttonClick+="9"
            buttonComma.id -> {
                if (!comma && buttonClick.isNotEmpty()) {
                    buttonClick += "."
                    comma = true
                }
                else if (buttonClick.isEmpty()) {
                    buttonClick += "0."
                    comma = true
                }
            }
            buttonSign.id -> {
                buttonClick = try {
                    if (buttonClick[0]=='-') buttonClick.substring(1)
                    else "-$buttonClick"
                } catch (e:Exception) {
                    "0"
                }
            }
        }
        textViewOutput.text = buttonClick
    }

    fun operationClick(view: View) {
        isNewOp = true
        comma = false
        firstNumber = textViewOutput.text.toString()
        val buttonSelect = view as Button
        when(buttonSelect.id) {
            buttonPlus.id -> operation="+"
            buttonMinus.id -> operation="-"
            buttonMultiplication.id -> operation="*"
            buttonDivision.id -> operation="/"
        }
    }

    fun equalsClick(view: View) {
        val newNumber = textViewOutput.text.toString()
        var result = 0.0
        when(operation) {
            "+" -> result=firstNumber.toDouble()+newNumber.toDouble()
            "-" -> result=firstNumber.toDouble()-newNumber.toDouble()
            "*" -> result=firstNumber.toDouble()*newNumber.toDouble()
            "/" -> result=firstNumber.toDouble()/newNumber.toDouble()
        }

        val longRes = result.toLong()
        if (result==longRes.toDouble())
            textViewOutput.text=longRes.toString()
        else
            textViewOutput.text=result.toString()
        isNewOp = true
    }

    fun acClick(view: View) {
        textViewOutput.text = "0"
        isNewOp = true
        comma = false
    }

    fun percentClick(view: View) {
        val percent = textViewOutput.text.toString().toDouble()/100
        textViewOutput.text = percent.toString()
        isNewOp = true
        comma = false
    }

}