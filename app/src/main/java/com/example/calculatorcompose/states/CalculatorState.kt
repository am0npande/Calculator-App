package com.example.calculatorcompose.states

import com.example.calculatorcompose.events.CalculatorOperation

data class CalculatorState(
    val number1:String = "",
    val number2:String = "",
    val operation: CalculatorOperation? = null,
    val Total:String = ""
)

//changable things in app are written in state file