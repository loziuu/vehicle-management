package pl.loziuu.ivms.model.vehicle.domain

import pl.loziuu.ivms.model.repair.domain.RepairDto

interface VehicleService {
    fun add(details: VehicleDetails): Long
    fun delete(id: Long)
    fun addRepair(vehicleId: Long, repairDto: RepairDto): RepairDto
}