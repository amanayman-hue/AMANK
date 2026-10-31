package com.amank.family.core
import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor

class AmankVpnService : VpnService() {
    private var vpnInterface: ParcelFileDescriptor? = null
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val builder = Builder()
            .addAddress("10.0.0.2", 32)
            .addRoute("0.0.0.0", 0)
            .addDnsServer("1.1.1.3")
            .addDnsServer("1.0.0.3")
            .setSession("AMANK Protection")
        vpnInterface = builder.establish()
        return START_STICKY
    }
    override fun onDestroy() {
        vpnInterface?.close()
        super.onDestroy()
    }
}
