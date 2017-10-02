package pl.loziuu.ivms.model.vehicle.domain

import pl.loziuu.ivms.model.insurance.domain.InsuranceDto
import pl.loziuu.ivms.model.insurance.domain.InsuranceService
import pl.loziuu.ivms.model.insurance.exception.InsuranceNotFoundException
import pl.loziuu.ivms.model.repair.domain.RepairDto
import pl.loziuu.ivms.model.repair.domain.RepairService
import pl.loziuu.ivms.model.vehicle.query.RepairNotFoundException
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryDto
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryService

class VehicleFacade(val command: VehicleService,
                    val query: VehicleQueryService,
                    val insuranceService: InsuranceService,
                    val repairService: RepairService) {

    fun getAll(): List<VehicleQueryDto>
            = query.getAll()

    fun get(id: Long): VehicleQueryDto
            = query.get(id)

    fun add(details: VehicleDetails): Long
            = command.add(details)

    fun delete(id: Long)
            = command.delete(id)

    fun addInsurance(dto: InsuranceDto): InsuranceDto
            = insuranceService.add(dto)

    fun deleteInsurance(vehicleId: Long, insuranceId: Long) {
        val vehicleInsurances = get(vehicleId).insurances
        if (vehicleInsurances.any { insurance -> insurance.id == insuranceId })
            insuranceService.delete(insuranceId)
        else
            throw InsuranceNotFoundException()
    }

    fun addRepair(repairDto: RepairDto): RepairDto =
        command.addRepair(repairDto.vehicleId, repairDto)

    fun deleteRepair(vehicleId: Long, repairId: Long) {
        val vehicleRepairs = get(vehicleId).repairs
        if (vehicleRepairs.any { repair -> repair.id == repairId })
            repairService.delete(repairId)
        else
            throw RepairNotFoundException()
    }
}