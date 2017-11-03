package pl.loziuu.ivms.management.vehicle.ports.primary

import pl.loziuu.ivms.management.vehicle.query.VehicleQueryDto

interface VehicleQueryService {
    fun getAll(): List<VehicleQueryDto>
    fun get(id: Long): VehicleQueryDto
    fun getInsured(): List<VehicleQueryDto>
    fun getUninsured(): List<VehicleQueryDto>
}