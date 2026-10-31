package com.amank.family
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.amank.family.core.PrefsManager

class ParentDashboardActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = PrefsManager(this)
        val layout = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(40,40,40,40) }
        val banner = TextView(this).apply { text = "أسرتك بأمان مع أمانك \uD83D\uDCAA"; textSize = 20f }
        val qrBtn = Button(this)
        val status = TextView(this)
        val container = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        
        fun refresh() {
            container.removeAllViews()
            val count = prefs.getChildren().size
            prefs.getChildren().sorted().forEach { name ->
                container.addView(TextView(this).apply { text = "$name - تتبع | تحكم | وقت الشاشة | ابحث عن هاتفي" })
            }
            status.text = "تم ربط $count / 5"
            if(count >= 5) { qrBtn.visibility = View.GONE } 
            else { 
                qrBtn.visibility = View.VISIBLE
                qrBtn.text = if(count==0) "إضافة روحي عن طريق QR" else "إضافة روحي${count+1} عن طريق QR"
            }
        }
        qrBtn.setOnClickListener {
            val added = prefs.addChild()
            if(added != null) {
                Toast.makeText(this, "تم ربط $added", Toast.LENGTH_SHORT).show()
                refresh()
            }
        }
        layout.addView(banner); layout.addView(qrBtn); layout.addView(status); layout.addView(container)
        setContentView(layout)
        prefs.setRole("parent")
        refresh()
    }
}
