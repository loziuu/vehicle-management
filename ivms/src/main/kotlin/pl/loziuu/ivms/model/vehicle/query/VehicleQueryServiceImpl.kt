package pl.loziuu.ivms.model.vehicle.query

import pl.loziuu.ivms.model.vehicle.exception.VehicleNotFoundException

class VehicleQueryServiceImpl(val repository: VehicleQueryRepository) : VehicleQueryService {
    override fun getAll(): List<VehicleQueryDto> = repository.findAll()

    override fun get(id: Long): VehicleQueryDto = repository.findOne(id) ?: throw VehicleNotFoundException()
}

