package com.amank.family
import android.app.Activity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.amank.family.core.PrefsManager

class SubscriptionActivity : Activity() {
    data class Offer(val title: String, val priceText: String, val code: String)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = PrefsManager(this)
        val deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 80, 40, 40)
        }
        layout.addView(TextView(this).apply {
            text = "امانك - عروض الاشتراك\nاول اسبوع مجانا للجميع"
            textSize = 20f
        })
        val isTrialUsed = prefs.isTrialUsed(deviceId)
        val offers = listOf(
            Offer("3 افراد - شهري", "50 جنيه", "monthly_3"),
            Offer("5 افراد - شهري", "60 جنيه", "monthly_5"),
            Offer("3 افراد - ربع سنوي", "120 جنيه", "quarterly_3"),
            Offer("5 افراد - ربع سنوي", "150 جنيه", "quarterly_5")
        )
        offers.forEach { offer ->
            val btn = Button(this).apply {
                val trialText = if (!isTrialUsed) " + اسبوع مجاني" else " (تم استخدام المجاني)"
                text = "${offer.title}\n${offer.priceText}$trialText"
                setOnClickListener {
                    if (prefs.isDeviceBlocked(deviceId)) {
                        Toast.makeText(this@SubscriptionActivity, "الجهاز محظور", Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }
                    if (!isTrialUsed) prefs.markTrialUsed(deviceId)
                    prefs.setSubscription(offer.code)
                    Toast.makeText(this@SubscriptionActivity, "تم تفعيل: ${offer.title}", Toast.LENGTH_SHORT).show()
                }
            }
            layout.addView(btn)
        }
        layout.addView(TextView(this).apply {
            text = "\nطرق الدفع:\n- فودافون كاش\n- فوري\n- بطاقة بنكية\n- Google Play"
            textSize = 14f
            setPadding(0, 30, 0, 0)
        })
        setContentView(layout)
    }
}
