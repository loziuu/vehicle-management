package pl.loziuu.ivms.vehicle.domain

import pl.loziuu.ivms.insurance.domain.InsuranceDto
import pl.loziuu.ivms.repair.domain.RepairDetails

interface VehicleService {
    fun add(details: VehicleDetails): Long
    fun delete(id: Long)
    fun addRepair(vehicleId: Long, details: RepairDetails)
    fun deleteRepair(vehicleId: Long, repairId: Long)
    fun removeInsurance(vehicleId: Long, insuranceId: Long)
    fun addInsurance(vehicleId: Long, dto: InsuranceDto)
}