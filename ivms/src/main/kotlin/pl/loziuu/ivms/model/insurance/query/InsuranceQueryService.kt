package pl.loziuu.ivms.model.insurance.query

import pl.loziuu.ivms.model.insurance.domain.Insurance

interface InsuranceQueryService {
    fun getVehicleInsurances(vehicleId: Long): List<InsuranceQueryDto>
    fun get(id: Long): InsuranceQueryDto
}