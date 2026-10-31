package com.amank.family

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.amank.family.core.BillingManager
import com.amank.family.core.PrefsManager

class SubscriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val billing = BillingManager(this)
        
        val btnMonthly = Button(this).apply { text = "7 أيام مجانا ثم 50 ج / شهر" }
        val btn3Months = Button(this).apply { text = "7 أيام مجانا ثم 120 ج / 3 شهور (أوفر)" }
        val btnVoda = Button(this).apply { text = "دفع فودافون كاش 010XXXXXXXX - ارسل صورة الإيصال" }

        btnVoda.setOnClickListener {
            // هنا المستخدم يحول على الرقم ويرفع صورة
            // بعد التأكيد: PrefsManager(this).setPremium(true) + setExpiry(now+30days)
            PrefsManager(this).setPremium(true)
            PrefsManager(this).setExpiry(System.currentTimeMillis() + 30L*24*60*60*1000)
            finish()
        }

        billing.startConnection {
            billing.queryOffers { offers ->
                val monthly = offers.find { it.productId == "amank_monthly" }
                val three = offers.find { it.productId == "amank_3months" }
                btnMonthly.setOnClickListener { if(monthly!=null) billing.buy(this, monthly) }
                btn3Months.setOnClickListener { if(three!=null) billing.buy(this, three) }
            }
        }
    }
}
