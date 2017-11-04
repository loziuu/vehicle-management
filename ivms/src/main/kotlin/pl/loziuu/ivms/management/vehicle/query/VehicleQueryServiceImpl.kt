package pl.loziuu.ivms.management.vehicle.query

import pl.loziuu.ivms.management.vehicle.exception.VehicleNotFoundException
import pl.loziuu.ivms.management.vehicle.ports.secondary.VehicleQueryRepository
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleQueryService

class VehicleQueryServiceImpl(val repository: VehicleQueryRepository) : VehicleQueryService {
    override fun get(id: Long): VehicleQueryDto = repository.findOne(id) ?: throw VehicleNotFoundException()

    override fun getAll(): List<VehicleQueryDto> = repository.findAll()
}

