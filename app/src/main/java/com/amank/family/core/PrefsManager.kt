package com.amank.family.core
import android.content.Context
class PrefsManager(context: Context) {
    private val p = context.getSharedPreferences("amank", Context.MODE_PRIVATE)
    fun setRole(r: String){ p.edit().putString("role", r).apply() }
    fun getRole(): String? = p.getString("role", null)
    fun addChild(): String? {
        val cur = p.getStringSet("children", mutableSetOf())!!.toMutableSet()
        if(cur.size >= 5) return null
        val name = "روحي${cur.size+1}"
        cur.add(name); p.edit().putStringSet("children", cur).apply(); return name
    }
    fun getChildren(): Set<String> = p.getStringSet("children", emptySet())!!
}
