package pl.loziuu.ivms.management.vehicle.ports.primary

import pl.loziuu.ivms.maintenance.insurance.domain.InsuranceDto
import pl.loziuu.ivms.maintenance.repair.domain.RepairDetails
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails

interface VehicleService {
    fun add(details: VehicleDetails): Long
    fun delete(id: Long)
    fun addRepair(vehicleId: Long, details: RepairDetails)
    fun deleteRepair(vehicleId: Long, repairId: Long)
    fun removeInsurance(vehicleId: Long, insuranceId: Long)
    fun addInsurance(vehicleId: Long, dto: InsuranceDto)
}