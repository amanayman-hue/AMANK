package com.amank.family

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.amank.family.core.AmankForegroundService
import com.amank.family.core.PrefsManager

class ParentDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_dashboard)
        
        val prefs = PrefsManager(this)
        prefs.setRole(true)
        prefs.setFirstOpen()
        startService(Intent(this, AmankForegroundService::class.java))

        // الرسالة الدائمة في الواجهة
        findViewById<TextView>(R.id.tv_status).text = "أسرتك بأمان مع أمانك 💪\nالتطبيق يعمل - تحذير الحجب نشط"

        // زرار تتبع روحي 1-5
        findViewById<Button>(R.id.btn_track).setOnClickListener {
            // تختار من 1 ل 5 - تفتح الخريطة
        }
        // زرار التحكم في التطبيقات + وقت الشاشة
        findViewById<Button>(R.id.btn_apps).setOnClickListener {
            // يعرض تطبيقات الطفل ووقت الاستخدام
        }
        // زر ابحث عن هاتفي روحي 1-5
        findViewById<Button>(R.id.btn_find).setOnClickListener {
            // يرن هاتف الطفل
        }

        val btnSub = findViewById<Button>(R.id.btn_sub)
        if(prefs.isPremium()){
            btnSub.text = "مشترك وآمن ✅"
            if(prefs.isNeedWarn48h()) btnSub.text = "تنبيه: الاشتراك ينتهي خلال 48 ساعة!"
            btnSub.isEnabled = false
        } else {
            btnSub.text = "اشتراك فودافون كاش - 50 ج / شهر أو 120 ج / 3 شهور - 7 أيام مجانا"
            btnSub.setOnClickListener { startActivity(Intent(this, SubscriptionActivity::class.java)) }
        }
    }
}
