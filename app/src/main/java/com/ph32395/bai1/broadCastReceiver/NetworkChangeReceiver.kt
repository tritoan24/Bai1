package com.ph32395.bai1.broadCastReceiver


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

//Ke thua BroadcastReceiver cho phep nhan cac thong bao broadcast tu he thong
class NetworkChangeReceiver : BroadcastReceiver() {
    //    onReceive phuong thuc quan trong duoc goi khi co broadcast phu hop duc gui den
    override fun onReceive(context: Context?, intent: Intent?) {
//        context la 1 tham so cua phuong thuc onReceiver cung cap quyen truy cap vao tai nguyen
//        va dich vu cua Android
//        as ConnectivityManager la 1 phep ep kieu (type cast) trong Kotlin
//        Dong nay dung de truy cap thong tin ket noi mang tu thiet bi
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    activeNetwork dai dien cho mang dang hoat dong
        val network = connectivityManager.activeNetwork
//    NetworkCapabilities cung cap thong tin chi tiet ve cac kha nang cua mang hien tai
        val capabilities = connectivityManager.getNetworkCapabilities(network)
//    hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) kiem tra thiet bi co dang ket noi Internet hay khong
        val isConnected = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

//    Tao thong bao trang thai ket noi
        val message = if (isConnected) "Network Connected" else "Network Disconnected"
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}