package com.student.applicationcomponent

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.student.CountDownTimers
import com.student.CountDownTimers.Companion.COUNT_DOWN

class MainActivity : AppCompatActivity() {
    var countdown: TextView ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val start = findViewById<Button>(R.id.start)
        val stop = findViewById<Button>(R.id.stop)
         countdown = findViewById<TextView>(R.id.countdownss)



        start.setOnClickListener {
            registerReceiver(br, IntentFilter(COUNT_DOWN))
            startService(Intent(this,CountDownTimers::class.java))
        }
        stop.setOnClickListener {
            countdown!!.text = "0"
            try {
                unregisterReceiver(br)
            } catch (e: Exception) {
            }
            stopService(Intent(this,CountDownTimers::class.java))
        }

    }

    override fun onPause() {
        super.onPause()
        try {
            unregisterReceiver(br)
        } catch (e: Exception) {
        }
    }

    private val br: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent:Intent) {
            countdown!!.text = intent.getStringExtra("cttimer")
        }
    }

    override fun onResume() {
        registerReceiver(br, IntentFilter(COUNT_DOWN))
        super.onResume()
    }
}