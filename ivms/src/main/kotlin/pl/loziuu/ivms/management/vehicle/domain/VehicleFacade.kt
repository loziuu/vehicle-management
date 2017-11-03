package pl.loziuu.ivms.management.vehicle.domain

import pl.loziuu.ivms.maintenance.insurance.domain.InsuranceDto
import pl.loziuu.ivms.maintenance.repair.domain.RepairDetails
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleQueryService
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleService
import pl.loziuu.ivms.management.vehicle.query.VehicleQueryDto

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