package ecb.manifest.kowalski.obd_scan.obd

import android.bluetooth.BluetoothSocket
import com.pnuema.android.obd.commands.OBDCommand
import com.pnuema.android.obd.enums.ObdModes
import com.pnuema.android.obd.models.PID

class ObdManager() {
    fun getRpm(socket: BluetoothSocket): String? {
        val pid = PID(ObdModes.MODE_01, RPM_CODE)
        val command = OBDCommand(pid)
        command.run(socket.inputStream, socket.outputStream)

        return pid.calculatedResultString
    }

    companion object PIDCodes {
        private const val COOLANT_TEMPERATURE_CODE: String = "05"
        private const val RPM_CODE: String = "0C"
        private const val ENGINE_THROTTLE_CODE: String = "17"

        private const val FUEL_LEVEL_CODE: String = "2F"
        private const val FUEL_CONSUMPTION_RATE_CODE: String = "5E"

        private const val OXYGEN_SENSOR_CODE: String = "19"
    }
}