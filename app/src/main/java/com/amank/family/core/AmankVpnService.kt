package com.amank.family.core

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
class AmankForegroundService : Service() {
    override fun onBind(i: Intent?): IBinder? = null
    override fun onStartCommand(intent: Intent?, f: Int, id: Int): Int {
        val isParent = PrefsManager(this).isParent()
        val text = if(isParent) "أسرتك بأمان مع أمانك 💪" else "روحي انت بأمان مع أمانك ❤️"
        
        if (Build.VERSION.SDK_INT >= 26) {
            val ch = NotificationChannel("amank", "Amank", NotificationManager.IMPORTANCE_LOW)
            getSystemService(NotificationManager::class.java).createNotificationChannel(ch)
        }
        val n = if (Build.VERSION.SDK_INT >= 26) Notification.Builder(this, "amank").setContentTitle("Amank Family").setContentText(text).setSmallIcon(android.R.drawable.ic_dialog_info).setOngoing(true).build()
        else Notification.Builder(this).setContentTitle("Amank Family").setContentText(text).setSmallIcon(android.R.drawable.ic_dialog_info).setOngoing(true).build()
        startForeground(1, n)
        return START_STICKY
    }
}
