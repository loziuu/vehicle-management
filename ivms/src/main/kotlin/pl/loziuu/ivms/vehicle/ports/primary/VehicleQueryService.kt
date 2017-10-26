package pl.loziuu.ivms.vehicle.ports.primary

import pl.loziuu.ivms.vehicle.query.VehicleQueryDto

interface VehicleQueryService {
    fun getAll(): List<VehicleQueryDto>
    fun get(id: Long): VehicleQueryDto
    fun getInsured(): List<VehicleQueryDto>
    fun getUninsured(): List<VehicleQueryDto>
}