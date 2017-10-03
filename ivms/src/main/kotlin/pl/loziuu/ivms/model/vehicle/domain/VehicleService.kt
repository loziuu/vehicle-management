package pl.loziuu.ivms.model.vehicle.domain

import pl.loziuu.ivms.model.insurance.domain.InsuranceDto
import pl.loziuu.ivms.model.repair.domain.RepairDetails

interface VehicleService {
    fun add(details: VehicleDetails): Long
    fun delete(id: Long)
    fun addRepair(vehicleId: Long, details: RepairDetails)
    fun deleteRepair(vehicleId: Long, repairId: Long)
    fun removeInsurance(vehicleId: Long, insuranceId: Long)
    fun addInsurance(vehicleId: Long, dto: InsuranceDto)
}