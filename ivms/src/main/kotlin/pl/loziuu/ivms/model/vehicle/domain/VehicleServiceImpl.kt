package pl.loziuu.ivms.model.vehicle.domain

import pl.loziuu.ivms.model.vehicle.exception.VehicleNotFoundException

class VehicleServiceImpl(val repository: VehicleRepository) : VehicleService {
    override fun add(dto: VehicleDto): VehicleDto = repository.save(dto.toEntity()).toDto()

    override fun delete(id: Long) {
        if (repository.exists(id))
            repository.delete(id)
        else
            throw VehicleNotFoundException()
    }
}