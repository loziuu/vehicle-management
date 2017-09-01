package pl.loziuu.ivms.model.insurance.query

interface InsuranceQueryService {
    fun getVehicleInsurances(vehicleId: Long): List<InsuranceQueryDto>
    fun get(id: Long): InsuranceQueryDto
}