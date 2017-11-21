package pl.loziuu.ivms.management.fleet.port

import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails

interface FleetCommand {
    fun createVehicle(fleetId: Long, details: VehicleDetails): Long
    fun createFleet(name: String): Long
    fun removeVehicle(fleetId: Long, vehicleId: Long)
}
