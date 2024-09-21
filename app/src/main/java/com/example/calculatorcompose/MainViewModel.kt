package com.example.calculatorcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculatorcompose.events.CalculatorActions
import com.example.calculatorcompose.events.CalculatorOperation
import com.example.calculatorcompose.states.CalculatorState

class MainViewModel : ViewModel(){

    var state by mutableStateOf(CalculatorState())

    fun onAction(action: CalculatorActions){
        when(action){
            CalculatorActions.Calculate -> performCalculation()
            CalculatorActions.Clear -> state = CalculatorState()
            CalculatorActions.Decimal -> enterDecimal()
            CalculatorActions.Delete -> performDelete()
            is CalculatorActions.Number -> enterNumber(action.number)
            is CalculatorActions.Operation -> enterOperation(action.operation)
            CalculatorActions.AddDoubleZeros -> AddDoubleZeros()
        }
    }

    private fun AddDoubleZeros() {
        if(state.number1.isNotBlank()){
            state = state.copy(state.number1.plus("00"))
        }
        if(state.operation != null && state.number2.isNotBlank()){
            state = state.copy(state.number2.plus("00"))
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(state.number1.isNotBlank()){
            state = state.copy(operation = operation)
        }


    }

    private fun enterNumber(number: Int) {

        if(state.operation == null){
            if(state.number1.length >= 8){
                return
            }
            state = state.copy(number1 = state.number1 + number)
            return
        }
        if(state.number2.length >= 8){
            return
        }
        state = state.copy(number2 = state.number2 + number)

    }

    private fun performDelete() {
        when{
            state.number2.isNotBlank() -> state = state.copy(number2 = state.number2.dropLast(1))
            state.operation != null -> state = state.copy(operation = null)
            state.number1.isNotBlank() -> state = state.copy(number1 = state.number1.dropLast(1))
        }
    }

    private fun enterDecimal() {
        if(state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()){
            state = state.copy(number1 = state.number1 + ".")
        }
        if(!state.number2.contains(".") && state.number2.isNotBlank()){
            state = state.copy(number1 = state.number2 + ".")
        }
    }

    private fun performCalculation() {

        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()

        var result = when(state.operation){
            CalculatorOperation.Add -> number1?.plus(number2!!)
            CalculatorOperation.Divide -> number1!! / number2!!
            CalculatorOperation.Modulus -> number1!! % number2!!
            CalculatorOperation.Multiply -> number1!!.times(number2!!)
            CalculatorOperation.Sub -> number1!!.minus(number2!!)
            null -> return
        }
        // Format the result string based on its value
        val resultString = if (result.toString().endsWith(".0")) result!!.toInt().toString() else result.toString()
        state = state.copy(
            number1 = resultString.take(15),
            number2 = "",
            operation = null
        )

    }
}