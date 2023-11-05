package com.example.os_project


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.DelicateCoroutinesApi

import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FactorialCalculatorApp()
        }
    }
}
@Composable
fun FactorialCalculatorApp() {
    var executionTime by remember { mutableStateOf(0L) }
    var result by remember { mutableStateOf(0L) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                    val time = measureTimeMillis {
                        result = FactorialCalculator().performComplexComputationWithThreads(100,3)?.toLong()!!
                    }
                    executionTime = time

            },
            modifier = Modifier.size(200.dp)
        ) {
            Text("Java Threads Execution")
        }
        Text(text = "Computing e^(Sin(x) * Cos(x)) for 0<=x<=100,with 3 threads")

        Button(
            onClick = {
                    val time = measureTimeMillis {
                        result = FactorialCalculator().performComplexComputationWithThreadsC(100,3).toLong()
                    }
                    executionTime = time

            },
            modifier = Modifier.size(200.dp)
        ) {
            Text("C++ Threads Execution")
        }

        Text(
            "Execution Time: ${executionTime}ms\nResult: $result",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFactorialCalculatorApp() {
    FactorialCalculatorApp()
}

