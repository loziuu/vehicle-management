package pl.loziuu.ivms.model.vehicle.domain

import pl.loziuu.ivms.model.insurance.domain.InsuranceDto
import pl.loziuu.ivms.model.insurance.domain.InsuranceFactory
import pl.loziuu.ivms.model.repair.domain.RepairDetails
import pl.loziuu.ivms.model.repair.domain.RepairFactory
import pl.loziuu.ivms.model.vehicle.exception.VehicleNotFoundException

class VehicleServiceImpl(val repository: VehicleRepository) : VehicleService {
    override fun add(details: VehicleDetails): Long {
        val vehicle = VehicleFactory.create(details)
        return repository.save(vehicle).id
    }

    override fun addRepair(vehicleId: Long, details: RepairDetails) {
        val vehicle = repository.findOne(vehicleId)
        vehicle.addRepair(RepairFactory.create(details))
        repository.save(vehicle)
    }

    override fun addInsurance(vehicleId: Long, dto: InsuranceDto) {
        val vehicle = repository.findOne(vehicleId)
        vehicle.addInsurance(InsuranceFactory.create(dto))
        repository.save(vehicle)
    }

    override fun deleteRepair(vehicleId: Long, repairId: Long) {
        val vehicle = repository.findOne(vehicleId)
        vehicle.deleteRepair(repairId)
        repository.save(vehicle)
    }

    override fun removeInsurance(vehicleId: Long, insuranceId: Long) {
        val vehicle = repository.findOne(vehicleId)
        vehicle.removeInsurance(insuranceId)
        repository.save(vehicle)
    }

    override fun delete(id: Long) {
        if (repository.exists(id))
            repository.delete(id)
        else
            throw VehicleNotFoundException()
    }
}
