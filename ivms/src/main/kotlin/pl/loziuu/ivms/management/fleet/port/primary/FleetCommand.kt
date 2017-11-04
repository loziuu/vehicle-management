package pl.loziuu.ivms.management.fleet.port.primary

import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails

interface FleetCommand {
    fun addVehicle(fleetId: Long, details: VehicleDetails): Long
    fun createFleet(name: String): Long
    fun removeVehicle(fleetId: Long, vehicleId: Long)
}
