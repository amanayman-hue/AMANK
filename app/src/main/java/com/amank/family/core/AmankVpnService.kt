package com.amank.family.core

import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor

class AmankVpnService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startVpn()
        return START_STICKY
    }

    private fun startVpn() {
        try {
            vpnInterface?.close()
            val builder = Builder()
                .addAddress("10.0.0.2", 32)
                .addRoute("0.0.0.0", 0)
                .addRoute("::", 0)
                .addDnsServer("1.1.1.3")
                .addDnsServer("1.0.0.3")
                .addDnsServer("9.9.9.11")
                .addDnsServer("149.112.112.112")
                .setSession("AMANK Family Protection")
                .setMtu(1500)
                .setBlocking(true)

            vpnInterface = builder.establish()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        vpnInterface?.close()
        super.onDestroy()
    }
}
