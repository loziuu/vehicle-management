package pl.loziuu.ivms.management.application

import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.management.fleet.port.FleetCommand
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import javax.transaction.Transactional

@ApplicationService
@Transactional
class ManagementService(var command: FleetCommand) {

    fun createFleet(name: String): Long {
        return command.createFleet(name)
    }

    fun addVehicle(fleetId: Long, vehicleDetails: VehicleDetails) : Long {
        return command.createVehicle(fleetId, vehicleDetails)
    }

    fun removeVehicle(fleetId: Long, vehicleId: Long) {
        return command.removeVehicle(fleetId, vehicleId)
    }
}
