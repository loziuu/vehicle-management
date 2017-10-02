package pl.loziuu.ivms.model.vehicle.domain

import pl.loziuu.ivms.model.repair.domain.RepairDto
import pl.loziuu.ivms.model.vehicle.exception.VehicleNotFoundException

class VehicleServiceImpl(val repository: VehicleRepository) : VehicleService {

    override fun add(details: VehicleDetails): Long {
        val vehicle = VehicleFactory.create(details)
        return repository.save(vehicle).id
    }

    override fun addRepair(vehicleId: Long, repairDto: RepairDto): RepairDto {
        val vehicle = repository.findOne(vehicleId)
        vehicle.addRepair(repairDto.toEntity())
        return repairDto
    }


    override fun delete(id: Long) {
        if (repository.exists(id))
            repository.delete(id)
        else
            throw VehicleNotFoundException()
    }
}
