package pl.loziuu.ivms.model.vehicle.domain

interface VehicleService {
    fun add(dto: VehicleDto): VehicleDto
    fun delete(id: Long)
}