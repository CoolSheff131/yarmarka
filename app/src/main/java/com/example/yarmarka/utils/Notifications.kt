package com.example.yarmarka.utils

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.yarmarka.R

class Notifications {
    companion object {
        private val CHANNEL_ID = "channel_id"
        private val notificationId = 101
        //Делает уведомление принимает контекст заголовок и текст (опционально иконку)
        fun sendNotification(context: Context,title: String, content: String,icon: Int = R.drawable.ic_account){
            val builder = context?.let {
                NotificationCompat.Builder(it,CHANNEL_ID)
                    .setSmallIcon(icon)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }
            if (builder != null) {
                with(context?.let { NotificationManagerCompat.from(it) }){
                    this?.notify(notificationId,builder.build())
                }
            }
        }
    }
}