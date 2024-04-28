package com.bsccs.calculatorapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var textview1 : TextView
    private lateinit var textview2 : TextView
    private lateinit var textview3 : TextView
    private lateinit var textview4 : TextView
    private lateinit var textview5 : TextView
    private lateinit var textview6 : TextView
    private lateinit var textview7 : TextView
    private lateinit var textview8 : TextView
    private lateinit var textview9 : TextView
    private lateinit var textview0 : TextView
    private lateinit var addition : TextView
    private lateinit var subtraction : TextView
    private lateinit var division : TextView
    private lateinit var multiplication : TextView
    private lateinit var equalsto: TextView
    private lateinit var clear : TextView
    private lateinit var back : TextView
    private lateinit var openparan : TextView
    private lateinit var closeparan : TextView
    private lateinit var dot : TextView
    private lateinit var expressiontv : TextView
    private lateinit var resulttv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Numbers

        textview1= findViewById(R.id.num1)
        textview2= findViewById(R.id.num2)
        textview3= findViewById(R.id.num3)
        textview4= findViewById(R.id.num4)
        textview5= findViewById(R.id.num5)
        textview6= findViewById(R.id.num6)
        textview7= findViewById(R.id.num7)
        textview8= findViewById(R.id.num8)
        textview9= findViewById(R.id.num9)
        textview0= findViewById(R.id.num0)

        // Operators
        addition= findViewById(R.id.addition)
        subtraction= findViewById(R.id.substraction)
        division= findViewById(R.id.division)
        multiplication= findViewById(R.id.multiplication)

        // buttons

        clear= findViewById(R.id.clear)
        openparan= findViewById(R.id.openBrac)
        closeparan= findViewById(R.id.closebrac)
        dot= findViewById(R.id.dot)
        back= findViewById(R.id.back)
        equalsto= findViewById(R.id.equalsto)

        // Displays
        expressiontv= findViewById(R.id.expression)
        resulttv= findViewById(R.id.result)

        //Logic
        clear.setOnClickListener {
            expressiontv.text=""
            resulttv.text=""
        }
        back.setOnClickListener {
            val string = expressiontv.text.toString()
            if (string.isNotEmpty()){
                expressiontv.text=string.substring(0,string.length-1)
            }
            resulttv.text=""
        }

        equalsto.setOnClickListener {
            try {
                val expression = ExpressionBuilder(expressiontv.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    resulttv.text = longResult.toString()
                else
                    resulttv.text=result.toString()

            }catch (e:Exception){
                Toast.makeText(this,"Exception message"+e.message,Toast.LENGTH_SHORT).show()

            }
        }

        textview0.setOnClickListener { appendOnExpression("0", true) }
        textview1.setOnClickListener { appendOnExpression("1", true) }
        textview2.setOnClickListener { appendOnExpression("2", true) }
        textview3.setOnClickListener { appendOnExpression("3", true) }
        textview4.setOnClickListener { appendOnExpression("4", true) }
        textview5.setOnClickListener { appendOnExpression("5", true) }
        textview6.setOnClickListener { appendOnExpression("6", true) }
        textview7.setOnClickListener { appendOnExpression("7", true) }
        textview8.setOnClickListener { appendOnExpression("8", true) }
        textview9.setOnClickListener { appendOnExpression("9", true) }

        addition.setOnClickListener { appendOnExpression("+", false) }
        subtraction.setOnClickListener { appendOnExpression("-", false) }
        multiplication.setOnClickListener { appendOnExpression("*", false) }
        division.setOnClickListener { appendOnExpression("/", false) }

        dot.setOnClickListener { appendOnExpression(".", true) }
        openparan.setOnClickListener { appendOnExpression("(", true) }
        closeparan.setOnClickListener { appendOnExpression(")", true) }
    }
    private fun appendOnExpression(string: String, canClear: Boolean){
        if(resulttv.text.isNotEmpty()){
            expressiontv.text=""
        }

        if (canClear){
            resulttv.text=""
            expressiontv.append(string)

        }else{
            expressiontv.append(resulttv.text)
            expressiontv.append(string)
            resulttv.text=""
        }
    }
}
