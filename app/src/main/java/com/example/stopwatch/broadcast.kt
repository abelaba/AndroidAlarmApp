package com.example.stopwatch

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log

class broadcast: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {



        var i = Intent(context,stopalarm::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)


        context?.startActivity(i)

    }




}