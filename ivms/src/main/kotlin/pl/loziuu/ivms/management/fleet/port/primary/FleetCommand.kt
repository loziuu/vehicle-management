package pl.loziuu.ivms.management.fleet.port.primary

import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails

interface FleetCommand {
    fun addVehicle(fleetId: Long, details: VehicleDetails)
}
