package com.amank.family

import android.app.Activity
import android.os.Bundle
import android.graphics.Typeface
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class SubscriptionActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }

        val title = TextView(this).apply {
            text = "اشتراك AMANK Family"
            textSize = 25f
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val description = TextView(this).apply {
            text = """
                اختر خطة الحماية المناسبة لأسرتك.

                الاشتراك الشهري: 50 جنيهًا
                الاشتراك لثلاثة أشهر: 120 جنيهًا

                تجربة مجانية: 7 أيام

                نظام الدفع قيد التجهيز.
                لن يتم تفعيل الاشتراك قبل التحقق من الدفع.
            """.trimIndent()
            textSize = 17f
            gravity = Gravity.CENTER
            setPadding(0, 24, 0, 24)
        }

        val button = Button(this).apply {
            text = "العودة"
            setOnClickListener {
                finish()
            }
        }

        layout.addView(title)
        layout.addView(description)
        layout.addView(button)

        setContentView(layout)
    }
}
