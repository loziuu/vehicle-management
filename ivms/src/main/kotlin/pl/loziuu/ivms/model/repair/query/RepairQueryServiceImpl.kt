package pl.loziuu.ivms.model.repair.query

class RepairQueryServiceImpl(val repository: RepairQueryRepository): RepairQueryService {
    override fun getVehicleRepairs(vehicleId: Long): List<RepairQueryDto> =
        repository.findAll();
}