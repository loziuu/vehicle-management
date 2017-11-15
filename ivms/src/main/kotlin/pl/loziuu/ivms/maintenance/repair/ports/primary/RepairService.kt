package pl.loziuu.ivms.maintenance.repair.ports.primary

import java.time.LocalDate

interface RepairService {
    fun registerRepair(vehicleId: Long, description: String, date: LocalDate, cost: Double)
}