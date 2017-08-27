package pl.loziuu.ivms.model.insurance.domain

import pl.loziuu.ivms.model.insurance.query.InsuranceQueryDto
import pl.loziuu.ivms.model.insurance.query.InsuranceQueryService

class InsuranceFacade(val command: InsuranceService, val query: InsuranceQueryService) {
    fun getInsurance(id: Long): InsuranceQueryDto = query.get(id)
    fun getVehicleInsurances(vehicleId: Long): List<InsuranceQueryDto> = query.getVehicleInsurances(vehicleId)
    fun addInsurance(dto: InsuranceDto): InsuranceDto = command.add(dto)
}
