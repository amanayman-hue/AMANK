package com.amank.family
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.amank.family.core.PrefsManager

class ParentDashboardActivity : Activity() {
    private lateinit var prefs: PrefsManager
    private lateinit var statusText: TextView
    private lateinit var qrButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = PrefsManager(this)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 100, 40, 40)
        }
        statusText = TextView(this).apply { textSize = 18f }
        layout.addView(statusText)
        qrButton = Button(this).apply {
            text = "انشاء QR لربط روحي جديدة"
            setOnClickListener {
                val name = prefs.addChild()
                if (name != null) updateUI() else {
                    startActivity(Intent(this@ParentDashboardActivity, SubscriptionActivity::class.java))
                }
            }
        }
        layout.addView(qrButton)
        val subButton = Button(this).apply {
            text = "عروض الاشتراك"
            setOnClickListener {
                startActivity(Intent(this@ParentDashboardActivity, SubscriptionActivity::class.java))
            }
        }
        layout.addView(subButton)
        setContentView(layout)
        updateUI()
    }
    private fun updateUI() {
        val children = prefs.getChildren()
        statusText.text = "تم ربط: ${children.joinToString(", ")}\nالعدد: ${children.size}/5"
        if (children.size >= 5) {
            qrButton.text = "وصلت الحد الاقصى - جدد اشتراكك"
        }
    }
}
