package com.ph32395.bai1.service.boundService


import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ph32395.bai1.ui.theme.MyApplicationTheme

class BoundActivity : ComponentActivity() {
    private var calculationService: CalculationService? = null
    private var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                CalculationContent()
            }
        }
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as CalculationService.CalculationBinder
            calculationService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, CalculationService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }

    @Composable
    fun CalculationContent() {
        var num1 by remember {
            mutableStateOf("")
        }
        var num2 by remember {
            mutableStateOf("")
        }
        var result by remember {
            mutableStateOf<Int?>(null)
        }
        val context = LocalContext.current

        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = num1,
                onValueChange = {num1 = it},
                label = { Text(text = "Num 1")}
            )

            OutlinedTextField(value = num2,
                onValueChange = {num2 = it},
                label = { Text(text = "Num 2")}
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                if (isBound) {
                    val a = num1.toIntOrNull()
                    val b = num2.toIntOrNull()

                    if (a != null && b != null) {
                        result = calculationService?.calculationSum(a, b)
                    }
                }
            }) {
                Text(text = "Calculate Sum")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                if (isBound) {
                    val a = num1.toIntOrNull()
                    val b = num2.toIntOrNull()

                    if (a != null && b != null) {
                        result = calculationService?.calculationProduct(a, b)
                    }
                }
            }) {
                Text(text = "Calculation Product")
            }

            Spacer(modifier = Modifier.height(10.dp))

            result?.let {
                Text(text = "Result: $it",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600)
                )
            }
        }
    }

}