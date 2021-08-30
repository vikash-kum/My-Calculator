package com.vikashkum.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.ArithmeticException


class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view : View) {

            tvInput.append((view as Button).text)
        lastNumeric = true

    }

    fun onClear(view: View) {
        tvInput.text=""
        lastNumeric = false
        lastDot = false
    }


    fun onDicimalPoint(view: View) {

        if(lastNumeric && !lastDot) {
            tvInput.append(".")
            lastNumeric=false
            lastDot = true
        }
    }

    fun onEqual(view: View) {

        if(lastNumeric) {
            var tvLast = tvInput.text.toString()

            var prefix =""

            try{

                if(tvLast.startsWith("-")) {
                    prefix = "-"
                    tvLast = tvLast.substring(1)

                }

                if( tvLast.contains("-")) {
                    val splitValue = tvLast.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDot(( one.toDouble() - two.toDouble() ).toString() )
                    }

                if( tvLast.contains("+")) {
                    val splitValue = tvLast.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDot(( one.toDouble() + two.toDouble() ).toString() )                }

                if( tvLast.contains("/")) {
                    val splitValue = tvLast.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDot(( one.toDouble() / two.toDouble() ).toString() )                }

                if( tvLast.contains("*")) {
                    val splitValue = tvLast.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDot(( one.toDouble() * two.toDouble() ).toString() )                }

            } catch (e : Exception) {
                Toast.makeText(this, "Exception", Toast.LENGTH_SHORT).show()

            }
        }
    }


    private fun removeZeroAfterDot(result: String) :String {
        var value = result
        if(result.contains(".0")) {
            value = value.substring(0 , value.length-2)
        }

        return value
    }

    fun onOperator(view: View) {
        if(lastNumeric && !isOpertorAdd(tvInput.text.toString())) {

            tvInput.append((view as Button).text)
            lastDot=false
            lastNumeric=false
        }
    }

    private fun isOpertorAdd(value : String) : Boolean {

        return if(value.startsWith("-")) {
            false
        }
        else{
            value.contains("/") || value.contains("*") || value.contains("+")  || value.contains("-")
        }

    }


}


