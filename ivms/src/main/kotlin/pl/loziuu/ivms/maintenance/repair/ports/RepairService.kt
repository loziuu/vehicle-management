package pl.loziuu.ivms.maintenance.repair.ports

import java.time.LocalDate

interface RepairService {
    fun registerRepair(vehicleId: Long, description: String, date: LocalDate, cost: Double)
    fun removeRepair(vehicleId: Long, repairId: Long)
}