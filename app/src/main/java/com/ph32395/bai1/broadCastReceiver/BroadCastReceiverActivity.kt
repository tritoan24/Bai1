package com.ph32395.bai1.broadCastReceiver

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ph32395.bai1.ui.theme.MyApplicationTheme

class BroadCastReceiverActivity : ComponentActivity() {
    private val networkChangeReceiver = NetworkChangeReceiver()

    //    onCreate khoi tao trang thai hoat dong
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                NetworkChangeContent()
            }
        }
    }

    //    onStart duoc goi khi nguoi dung nhin thay giao dien
    override fun onStart() {
        super.onStart()
//    Tao ra bo loc filter de lang nghe hanh dong cua ket noi mang
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
//    Dang ky de lang nghe cac thay doi ket noi mang voi bo loc filter
        registerReceiver(networkChangeReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
//        Huy dang ky de ngung lang nghe su thay doi ket noi mang
        unregisterReceiver(networkChangeReceiver)
    }
}

@Composable
fun NetworkChangeContent() {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Network Change Receiver Example")
    }
}