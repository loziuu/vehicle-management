package pl.loziuu.ivms.vehicle.query

interface VehicleQueryService {
    fun getAll(): List<VehicleQueryDto>
    fun get(id: Long): VehicleQueryDto
    fun getInsured(): List<VehicleQueryDto>
    fun getUninsured(): List<VehicleQueryDto>
}