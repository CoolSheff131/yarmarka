package com.example.yarmarka

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.yarmarka.utils.fm
import android.content.IntentFilter
import android.util.Log
import android.view.View
import com.example.yarmarka.utils.Notifications


class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID = "channel_id"
    private val tickReceiver by lazy { makeBroadcastReceiver() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Yarmarka)
        setContentView(R.layout.activity_main)
        fm = supportFragmentManager
        createNotificationChannel()
    }
    override fun onResume() {
        super.onResume()
        //dateTimeTextView.text = getCurrentTimeStamp()
        Toast.makeText(this,"asd",Toast.LENGTH_LONG).show()
        registerReceiver(tickReceiver, IntentFilter(Intent.ACTION_TIME_TICK))
    }

//    override fun onPause() {
//        super.onPause()
//        try {
//            unregisterReceiver(tickReceiver)
//        } catch (e: IllegalArgumentException) {
//            Log.e("Broadcast", "Time tick Receiver not registered", e)
//        }
//    }

    private fun makeBroadcastReceiver(): BroadcastReceiver {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                if (intent?.action == Intent.ACTION_TIME_TICK) {
                    Notifications.sendNotification(context,"aa","ss")
                    //dateTimeTextView.text = getCurrentTimeStamp()
                }
            }
        }
    }
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description= descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
}