package com.amank.family.core

import android.content.Context
class PrefsManager(c: Context) {
    private val p = c.getSharedPreferences("amank", Context.MODE_PRIVATE)
    fun setPremium(v: Boolean) = p.edit().putBoolean("premium", v).apply()
    fun isPremium() = p.getBoolean("premium", false)
    fun setRole(isParent: Boolean) = p.edit().putBoolean("isParent", isParent).apply()
    fun isParent() = p.getBoolean("isParent", true)
    fun setExpiry(time: Long) = p.edit().putLong("expiry", time).apply()
    fun getExpiry() = p.getLong("expiry", 0)
    fun getFirstOpen(): Long = p.getLong("first_open", 0)
    fun setFirstOpen() { if(getFirstOpen()==0L) p.edit().putLong("first_open", System.currentTimeMillis()).apply() }
    fun isNeedWarn48h(): Boolean {
        val ex = getExpiry(); if(ex==0L) return false
        return (ex - System.currentTimeMillis()) < 48*60*60*1000
    }
}
