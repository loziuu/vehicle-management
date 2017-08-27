package pl.loziuu.ivms.model.vehicle.query

interface VehicleQueryService {
    fun getAll(): List<VehicleQueryDto>
    fun get(id: Long): VehicleQueryDto
}