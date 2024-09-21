package com.example.calculatorcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorcompose.events.CalculatorActions
import com.example.calculatorcompose.events.CalculatorOperation

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {


    val state = viewModel.state
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1C)), // Realme style dark background
        verticalArrangement = Arrangement.Bottom
    ) {
        // Display the calculator's history
//        Row(
//            Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            horizontalArrangement = Arrangement.End,
//            verticalAlignment = Alignment.Bottom
//        ) {
//            Text(
//                text = history,
//                fontSize = 30.sp,
//                color = Color.Gray,
//                textAlign = TextAlign.End,
//                modifier = Modifier.fillMaxWidth()
//            )
//        }

        // Display the calculator's output
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                fontSize = 60.sp,
                color = Color.White,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(20.dp))

        Column(Modifier.padding(5.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CalculatorItem("AC", Color(0xFFB83B3B)) {
                    viewModel.onAction(CalculatorActions.Clear)
                }
                CalculatorItem("%", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Operation(CalculatorOperation.Modulus))
                }
                CalculatorItem("cut", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Delete)

                }
                CalculatorItem("/", Color(0xFFFF9800)) {

                    viewModel.onAction(CalculatorActions.Operation(CalculatorOperation.Divide))
                }
            }

            Spacer(Modifier.height(10.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CalculatorItem("7", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Number(7))
                }
                CalculatorItem("8", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Number(8))
                }
                CalculatorItem("9", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Number(9))
                }
                CalculatorItem("*", Color(0xFFFF9800)) {
                    viewModel.onAction(CalculatorActions.Operation(CalculatorOperation.Multiply))

                }
            }

            Spacer(Modifier.height(10.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CalculatorItem("4", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Number(4))
                }
                CalculatorItem("5", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Number(5))
                }
                CalculatorItem("6", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Number(6))

                }
                CalculatorItem("-", Color(0xFFFF9800)) {
                    viewModel.onAction(CalculatorActions.Operation(CalculatorOperation.Sub))

                }
            }

            Spacer(Modifier.height(10.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CalculatorItem("1", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Number(1))
                }
                CalculatorItem("2", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Number(2))
                }
                CalculatorItem("3", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Number(3))
                }
                CalculatorItem("+", Color(0xFFFF9800)) {
                    viewModel.onAction(CalculatorActions.Operation(CalculatorOperation.Add))
                }
            }

            Spacer(Modifier.height(10.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CalculatorItem("00", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.AddDoubleZeros)
                }
                CalculatorItem("0", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Number(0))
                }
                CalculatorItem(".", Color(0xFF505050)) {
                    viewModel.onAction(CalculatorActions.Decimal)
                }
                CalculatorItem("=", Color(0xFF00C853)) {
                    viewModel.onAction(CalculatorActions.Calculate)
                    
                }
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
fun CalculatorItem(
    item: String,
    color: Color = Color.Gray,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(90.dp)
            .background(color = color, shape = CircleShape)
            .clickable { onClick() }
    ) {
        Text(
            text = item,
            style = typography.run {
                bodyLarge.copy(
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    MainScreen(viewModel = MainViewModel())
}
