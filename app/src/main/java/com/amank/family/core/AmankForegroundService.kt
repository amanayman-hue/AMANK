package com.amank.family.core

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder

class AmankForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        val isParent = PrefsManager(this).isParent()

        val message = if (isParent) {
            "أسرتك بأمان مع أمانك"
        } else {
            "روحي أنت بأمان مع أمانك"
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "amank",
                "Amank Protection",
                NotificationManager.IMPORTANCE_LOW
            )

            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }

        val notification = if (Build.VERSION.SDK_INT >= 26) {
            Notification.Builder(this, "amank")
                .setContentTitle("AMANK Family")
                .setContentText(message)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setOngoing(true)
                .build()
        } else {
            Notification.Builder(this)
                .setContentTitle("AMANK Family")
                .setContentText(message)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setOngoing(true)
                .build()
        }

        startForeground(1, notification)

        return START_STICKY
    }
}
