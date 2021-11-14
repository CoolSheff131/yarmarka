package com.example.yarmarka.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
//Получатель широковещательных сообщений с фильтром android.intent.action.TIME_TICK
class MyReceiver : BroadcastReceiver() {
    //Фукнция которая выполняется при получении сообщения
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("T_T","AAA")
        Notifications.sendNotification(context,"","")
    }
}