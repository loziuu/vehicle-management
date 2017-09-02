package pl.loziuu.ivms.model.repair.query

interface RepairQueryService {
    fun getVehicleRepairs(vehicleId: Long): List<RepairQueryDto>
}