package pl.loziuu.ivms.management.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.management.fleet.port.primary.FleetCommand
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import javax.transaction.Transactional

@ApplicationService
@Transactional
class ManagementService(@Autowired var command: FleetCommand) {

    fun createFleet(name: String): Long {
        return command.createFleet(name)
    }

    fun addVehicle(fleetId: Long, vehicleDetails: VehicleDetails) : Long {
        return command.addVehicle(fleetId, vehicleDetails)
    }

    fun removeVehicle(fleetId: Long, vehicleId: Long) {
        return command.removeVehicle(fleetId, vehicleId)
    }
}