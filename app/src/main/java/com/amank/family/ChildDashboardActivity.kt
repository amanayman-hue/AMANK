package com.amank.family

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.amank.family.core.AmankForegroundService
import com.amank.family.core.PrefsManager

class ChildDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PrefsManager(this).setRole(false)
        startService(Intent(this, AmankForegroundService::class.java))
        val tv = TextView(this)
        tv.text = "روحي انت بأمان مع أمانك ❤️\nأنت متصل بأمان مع العائلة"
        tv.textSize = 22f
        setContentView(tv)
    }
}
