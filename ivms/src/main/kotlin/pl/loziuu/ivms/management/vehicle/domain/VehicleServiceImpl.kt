package pl.loziuu.ivms.management.vehicle.domain

import pl.loziuu.ivms.management.vehicle.exception.VehicleNotFoundException
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleService
import pl.loziuu.ivms.management.vehicle.ports.secondary.VehicleRepository

class VehicleServiceImpl(val repository: VehicleRepository) : VehicleService {

    override fun add(details: VehicleDetails): Long {
        val vehicle = VehicleFactory.create(details)
        return repository.save(vehicle).id
    }

    override fun delete(id: Long) {
        if (repository.exists(id))
            repository.delete(id)
        else
            throw VehicleNotFoundException()
    }
}
