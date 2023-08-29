package ecb.manifest.kowalski.obd_scan.bluetooth

sealed interface IConnectionResult {
    object ConnectionEstablished: IConnectionResult
    data class Error(val message: String): IConnectionResult
}
