package pl.loziuu.ivms.management.application

import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.management.fleet.port.FleetService
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import javax.transaction.Transactional

@ApplicationService
@Transactional
class ManagementService(var service: FleetService) {

    fun createFleet(name: String): Long = service.createFleet(name)

    fun moveVehicleToFleet(currentFleetId: Long, currentVehicleLocalId: Long, newFleetId: Long) {
        service.moveVehicleToFleet(currentFleetId, currentVehicleLocalId, newFleetId);
    }

    fun addVehicle(fleetId: Long, registration: String, vehicleDetails: VehicleDetails): Long =
        service.createVehicle(fleetId, registration, vehicleDetails)

    fun removeVehicle(fleetId: Long, vehicleId: Long) =
        service.removeVehicle(fleetId, vehicleId)

    fun removeFleet(fleetId: Long) =
        service.removeFleet(fleetId)
}
