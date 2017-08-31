package pl.loziuu.ivms.model.insurance.query

class InsuranceQueryServiceImpl(val repository: InstanceQueryRepository) : InsuranceQueryService {

    override fun getVehicleInsurances(vehicleId: Long): List<InsuranceQueryDto>
            = repository.findByVehicleId(vehicleId)

    override fun get(id: Long): InsuranceQueryDto
            = repository.findOne(id)
}