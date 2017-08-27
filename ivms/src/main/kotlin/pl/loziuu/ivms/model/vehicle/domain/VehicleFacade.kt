package pl.loziuu.ivms.model.vehicle.domain

import pl.loziuu.ivms.model.insurance.domain.InsuranceDto
import pl.loziuu.ivms.model.insurance.domain.InsuranceFacade
import pl.loziuu.ivms.model.insurance.query.InsuranceQueryDto
import pl.loziuu.ivms.model.vehicle.exception.VehicleNotFoundException
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryDto
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryService

class VehicleFacade(val command: VehicleService, val query: VehicleQueryService, val insuranceFacade: InsuranceFacade) {

    fun getAll(): List<VehicleQueryDto> = query.getAll()

    fun get(id: Long): VehicleQueryDto = query.get(id)

    fun add(dto: VehicleDto): VehicleDto = command.add(dto)

    fun delete(id: Long) = command.delete(id)

    fun addInsurance(dto: InsuranceDto): InsuranceDto = insuranceFacade.addInsurance(dto)
}