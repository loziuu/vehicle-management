package pl.loziuu.ivms.vehicle.domain

import pl.loziuu.ivms.insurance.domain.InsuranceDto
import pl.loziuu.ivms.repair.domain.RepairDetails
import pl.loziuu.ivms.vehicle.ports.primary.VehicleService
import pl.loziuu.ivms.vehicle.query.VehicleQueryDto
import pl.loziuu.ivms.vehicle.ports.primary.VehicleQueryService

class VehicleFacade(val command: VehicleService,
                    val query: VehicleQueryService) {

    fun getAll(): List<VehicleQueryDto>
            = query.getAll()

    fun get(id: Long): VehicleQueryDto
            = query.get(id)

    fun add(details: VehicleDetails): Long
            = command.add(details)

    fun delete(id: Long)
            = command.delete(id)

    fun addInsurance(vehicleId: Long, dto: InsuranceDto)
            = command.addInsurance(vehicleId, dto)

    fun deleteInsurance(vehicleId: Long, insuranceId: Long)
            = command.removeInsurance(vehicleId, insuranceId)

    fun addRepair(vehicleId: Long, details: RepairDetails)
            = command.addRepair(vehicleId, details)

    fun deleteRepair(vehicleId: Long, repairId: Long)
            = command.deleteRepair(vehicleId, repairId)
}