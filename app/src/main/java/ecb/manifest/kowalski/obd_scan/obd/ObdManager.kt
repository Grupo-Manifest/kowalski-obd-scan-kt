package ecb.manifest.kowalski.obd_scan.obd

import androidx.lifecycle.LiveData
import ecb.manifest.kowalski.obd_scan.data.repository.WebSocketRepository
import javax.inject.Inject

class ObdManager @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
) {
    fun getRpm(): String? {
        sendCommand(RPM_CODE)
        return webSocketRepository.receivedMessage.value
    }

    fun getCoolantTemperature(): String? {
        sendCommand(COOLANT_TEMPERATURE_CODE)
        return webSocketRepository.receivedMessage.value
    }

    fun getEngineThrottle(): String? {
        sendCommand(ENGINE_THROTTLE_CODE)
        return webSocketRepository.receivedMessage.value
    }

    fun getFuelLevel(): String? {
        sendCommand(FUEL_LEVEL_CODE)
        return webSocketRepository.receivedMessage.value
    }

    fun getFuelConsumptionRate(): String? {
        sendCommand(FUEL_CONSUMPTION_RATE_CODE)
        return webSocketRepository.receivedMessage.value
    }

    fun getOxygenSensor(): String? {
        sendCommand(OXYGEN_SENSOR_CODE)
        return webSocketRepository.receivedMessage.value
    }

    private fun sendCommand(pidCode: Int) {
        val command = arrayListOf(0x01, pidCode).toString()
        webSocketRepository.sendMessage(command)
    }

    companion object PIDCodes {
        private const val COOLANT_TEMPERATURE_CODE: Int = 0x05
        private const val RPM_CODE: Int = 0x0C
        private const val ENGINE_THROTTLE_CODE: Int = 0x17

        private const val FUEL_LEVEL_CODE: Int = 0x2F
        private const val FUEL_CONSUMPTION_RATE_CODE: Int = 0x5E

        private const val OXYGEN_SENSOR_CODE: Int = 0x19
    }
}