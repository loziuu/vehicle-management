package pl.loziuu.ivms.management.application

import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.management.fleet.port.FleetCommand
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import javax.transaction.Transactional

@ApplicationService
@Transactional
class ManagementService(var command: FleetCommand) {

    fun createFleet(name: String): Long = command.createFleet(name)

    fun moveVehicleToFleet(currentFleetId: Long, currentVehicleLocalId: Long, newFleetId: Long) {
        command.moveVehicleToFleet(currentFleetId, currentVehicleLocalId, newFleetId);
    }

    fun addVehicle(fleetId: Long, vehicleDetails: VehicleDetails): Long =
            command.createVehicle(fleetId, vehicleDetails)

    fun removeVehicle(fleetId: Long, vehicleId: Long) =
            command.removeVehicle(fleetId, vehicleId)
}
