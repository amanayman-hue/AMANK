package com.amank.family
import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.amank.family.core.PrefsManager
class ChildDashboardActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv = TextView(this).apply {
            textSize = 22f
            setPadding(40,100,40,40)
            text = "روحي انت بأمان مع أمانك ❤️\n\nالحماية شغالة"
        }
        setContentView(tv)
        PrefsManager(this).setRole("child_linked")
    }
}
