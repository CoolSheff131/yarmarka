package com.example.yarmarka

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.yarmarka.utils.fm

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addNotification("test","test" )
        Toast.makeText(this, " asd",Toast.LENGTH_LONG)
        setTheme(R.style.Theme_Yarmarka)
        setContentView(R.layout.activity_main)

        fm = supportFragmentManager

    }

    private fun addNotification(title: String, body: String) {
        val builder: Notification.Builder? = Notification.Builder(this)
            .setSmallIcon(R.drawable.launch_screen) //set icon for notification
            .setContentTitle(title) //set title of notification
            .setContentText(body) //this is notification message
        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", body)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder?.setContentIntent(pendingIntent)
        // Add as notification
        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        manager.notify(0, builder?.build())
        Log.v("AS","ASFAS")
    }
}