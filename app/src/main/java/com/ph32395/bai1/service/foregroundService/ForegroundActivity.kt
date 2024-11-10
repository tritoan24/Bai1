package com.ph32395.bai1.service.foregroundService

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ph32395.bai1.ui.theme.MyApplicationTheme

class ForegroundActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                ForegroundMusicContent()
            }
        }
    }
}

@Composable
fun ForegroundMusicContent() {
    val context = LocalContext.current
    var isPalaying by remember {
        mutableStateOf(true)
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            startMusicService(context)
            isPalaying = true
        }) {
            Text(text = "Start Music Service")
        }

        Button(onClick = {
            stopMusicService(context)
            isPalaying = false
        }) {
            Text(text = "Stop Music Service")
        }

        Button(onClick = {
            if (isPalaying) {
                pauseMusicService(context)
            } else {
                playMusicService(context)
            }
            isPalaying = !isPalaying
        }) {
            Text(text = if (isPalaying) "Pause Music Service" else "Play Music Service")
        }
    }
}

private fun startMusicService(context: Context) {
    val intent = Intent(context, MediaPlayerService::class.java)
    context.startService(intent)
}

private fun stopMusicService(context: Context) {
    val intent = Intent(context, MediaPlayerService::class.java).apply {
        action = MediaPlayerService.ACTION_STOP
    }
    context.startService(intent)
}

private fun pauseMusicService(context: Context) {
    val intent = Intent(context, MediaPlayerService::class.java).apply {
        action = MediaPlayerService.ACTION_PAUSE
    }
    context.startService(intent)
}

private fun playMusicService(context: Context) {
    val intent = Intent(context, MediaPlayerService::class.java).apply {
        action = MediaPlayerService.ACTION_PLAY
    }
    context.startService(intent)
}