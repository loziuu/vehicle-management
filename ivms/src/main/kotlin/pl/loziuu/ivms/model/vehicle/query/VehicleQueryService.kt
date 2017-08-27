package pl.loziuu.ivms.model.vehicle.query

interface VehicleQueryService {
    fun doesExists(id: Long): Boolean
    fun getAll(): List<VehicleQueryDto>
    fun get(id: Long): VehicleQueryDto
}