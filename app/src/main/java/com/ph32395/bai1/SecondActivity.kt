package com.ph32395.bai1


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.ph32395.bai1.ui.theme.MyApplicationTheme


class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Hien thi thong bao tai logcat
        Log.d("SecondActivity", "onCreate called")

//        Nhan du lieu duoc truyen tu man Main
        val receivedData = intent.getStringExtra("data_key")

        setContent {
            MyApplicationTheme {
                Box (
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = receivedData ?: "No data received")
                }
            }
        }
    }

    //    Activity duoc nhin thay
    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "onStart called")
    }

    //    Activity nhan duoc tuong tac tu nguoi dung va tro thanh hanh dong chinh
    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume called")
    }

    //    Activity khong la hd chinh, luu trang thai
    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause called")
    }

    //    Activity khong con nhin thay
    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop called")
    }

    //    Activity sap bi huy bo
    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy called")
    }
}