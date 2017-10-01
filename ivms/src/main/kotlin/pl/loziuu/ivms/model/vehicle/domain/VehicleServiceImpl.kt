package pl.loziuu.ivms.model.vehicle.domain

import pl.loziuu.ivms.model.vehicle.exception.VehicleNotFoundException

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