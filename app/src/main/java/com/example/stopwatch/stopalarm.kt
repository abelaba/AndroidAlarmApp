package com.example.stopwatch

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView

class stopalarm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopalarm)
        var mp  = MediaPlayer.create(applicationContext, R.raw.alarm_clock)
        mp.isLooping = true

        mp.start()

        val stopalarmbutton = findViewById<Button>(R.id.stopalarmButton)
        val video = findViewById<VideoView>(R.id.videoview)
        video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.alarmgif);
        video.start()

        stopalarmbutton.setOnClickListener{
            mp.stop()
            finish()
        }


    }
}