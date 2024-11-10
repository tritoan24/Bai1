package com.ph32395.bai1.service.backgroundService


import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.ph32395.bai1.R

class MusicService : Service() {
    //    Khai bao doi tuong MediaPlayer de phat nhac
    private lateinit var mediaPlayer: MediaPlayer

    //    Duoc goi khi bat dau khoi tao
    override fun onCreate() {
        super.onCreate()
        Log.d("MusicService", "Service Created")
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
//    Thiet lap de phat lai nhac vo han
        mediaPlayer.isLooping = true
    }

    //    Duoc goi khi dich vu khoi dong phuong thuc startService
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MusicService", "Service Started")
        mediaPlayer.start()
//    Yeu cau he thong khoi dong lại dich vu neu no bi huy
        return START_STICKY
    }

    //    Phuong thuc nay duoc goi khi dich vu duoc huy bang cach goi phuong thuc stopService()
//    hoac khi he thong quyet dinh dung
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MusicService", "Service Destroyed")
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    //    Duoc goi khi mot thanh phan khac muon ket noi voi dich vu
//    Tra ve null vi dich vu khong cung cap giao dien giao tiep
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}