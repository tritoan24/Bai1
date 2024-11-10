package com.ph32395.bai1


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.ph32395.bai1.ui.theme.MyApplicationTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Hien thi thong báo trong Logcat
        Log.d("MainActivity", "onCreate called")
        setContent {
            MyApplicationTheme {
                Box (
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
//                        Tao doi tuong Intent moi de khoi chay SecondActivity
                        val intent = Intent(this@MainActivity, SecondActivity::class.java)
//                        Them du lieu bo sung vao Intent, truyen du lieu tu Main sng Second
                        intent.putExtra("data_key", "Hello from MainActivity")
                        startActivity(intent)
                    }) {
                        Text(text = "Go to Second Activity")
                    }
                }
            }
        }
    }

//    Bat dau khi Activity duoc nhin thay
    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart called")
    }

//    Tuong tac voi nguoi dung va tro thanh hanh dong chinh
    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume called")
    }

//    Activity khong con la hoat dong chinh, luu trang thai hoac dung
    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause called")
    }

//    Activity khong con nhin thay va chuan bi an hoac bi bo
    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop called")
    }

//    Activity sap bi huy bo
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy called")
    }
}