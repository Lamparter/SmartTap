package dev.riverside.credit

import android.nfc.cardemulation.HostApduService
import android.os.Bundle

class SmartTapHCE : HostApduService() {
    override fun processCommandApdu(commandApdu: ByteArray, extras: Bundle): ByteArray {
        // TODO: Handle the APDU command here
        // (return a response APDU according to the commandApdu received)
        return byteArrayOf()
    }

    override fun onDeactivated(reason: Int) {
        // TODO: Handle deactivation here
        // (this method will be called whenever the NFC link is deactivated or lost)
    }
}

