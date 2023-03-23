package com.student

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder

class CountDownTimers : Service() {

    companion object {
        var COUNT_DOWN = "com.student.countdown_timer"
    }

    var intent: Intent = Intent(COUNT_DOWN)
    var ct: CountDownTimer? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        ct = object : CountDownTimer(30000, 1000) {
            override fun onTick(milliunits: Long) {
                intent.putExtra("cttimer", "" + milliunits / 1000)
                sendBroadcast(intent)
            }

            override fun onFinish() {

            }
        }
        ct!!.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        ct!!.cancel()
    }
}