package com.ph32395.bai1.service.foregroundService

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.ph32395.bai1.R

class MediaPlayerService : Service() {
    private val binder = MediaPlayerBinder()
    private lateinit var mediaPlayer: MediaPlayer
    private var isPaused = false

    inner class MediaPlayerBinder: Binder() {
        fun getService(): MediaPlayerService = this@MediaPlayerService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> {
                stopForeground(true)
                stopSelf()
            }

            ACTION_PAUSE -> {
                pauseMusic()
                createNotificationChannel()
                startForeground(NOTIFICATION_ID, createNotification(true))
            }
            ACTION_PLAY -> {
                playMusic()
                createNotificationChannel()
                startForeground(NOTIFICATION_ID, createNotification(false))
            }

            else -> {
                startMusic()
                createNotificationChannel()
                startForeground(NOTIFICATION_ID, createNotification(false))
            }
        }
        return START_NOT_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun startMusic() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    private fun pauseMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            isPaused = true
        }
    }

    private fun playMusic() {
        if (isPaused) {
            mediaPlayer.start()
            isPaused = false
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(isPaused: Boolean): Notification {
        val stopIntent = Intent(this, MediaPlayerService::class.java).apply {
            action = ACTION_STOP
        }
        val stopPendingIntent: PendingIntent = PendingIntent.getService(this, 0, stopIntent, PendingIntent.FLAG_IMMUTABLE)

        val pauseIntent = Intent(this, MediaPlayerService::class.java).apply {
            action = if (isPaused) ACTION_PLAY else ACTION_PAUSE
        }
        val pausePendingIntent: PendingIntent = PendingIntent.getService(this, 0, pauseIntent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Music Player")
            .setContentText(if (isPaused) "Music Paused" else "Playing music")
            .setSmallIcon(R.drawable.icon_music)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.icon_music))
            .addAction(R.drawable.icon_pause, if (isPaused) "Play" else "Pause", pausePendingIntent)
            .addAction(R.drawable.ic_play_arrow, "Stop", stopPendingIntent)
            .build()
    }

    companion object{
        const val CHANNEL_ID = "MediaPlayerServicesChannel"
        const val CHANNEL_NAME = "Media Player Service Channel"
        const val NOTIFICATION_ID = 1234
        const val ACTION_STOP = "ACTION_STOP"
        const val ACTION_PAUSE = "ACTION_PAUSE"
        const val ACTION_PLAY = "ACTION_PLAY"
    }
}