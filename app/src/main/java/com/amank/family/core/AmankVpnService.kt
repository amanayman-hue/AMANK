package com.amank.family.core

import android.net.VpnService
import android.content.Intent
import android.os.ParcelFileDescriptor

class AmankVpnService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        if (vpnInterface != null) {
            return START_STICKY
        }

        val builder = Builder()
            .setSession("AMANK Protection")
            .addAddress("10.10.10.1", 32)
            .addRoute("0.0.0.0", 0)
            .addDnsServer("1.1.1.1")
            .addDnsServer("1.0.0.1")
            .setMtu(1500)

        vpnInterface = builder.establish()

        return START_STICKY
    }

    override fun onDestroy() {
        vpnInterface?.close()
        vpnInterface = null
        super.onDestroy()
    }
}
