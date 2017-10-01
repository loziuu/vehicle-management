package pl.loziuu.ivms.model.vehicle.domain

object VehicleFactory {
    fun create(details: VehicleDetails): Vehicle {
        return Vehicle(0, details);
    }
}