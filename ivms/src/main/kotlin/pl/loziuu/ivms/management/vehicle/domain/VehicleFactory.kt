package pl.loziuu.ivms.management.vehicle.domain

import pl.loziuu.ivms.ddd.DomainValidationException

object VehicleFactory {
    fun create(registration: String, details: VehicleDetails): Vehicle {
        validate(details)
        return Vehicle(registration = registration, details = details)
    }

    private fun validate(details: VehicleDetails) {
        if (details.manufacturer.trim().length <= 0) {
            throw DomainValidationException("Manufacturer name can't be empty")
        }
        if (details.model.trim().length <= 0) {
            throw DomainValidationException("Model name can't be empty")
        }
        if (details.productionYear < 1950) {
            throw DomainValidationException("Can't add vehicle manufactured before 1949")
        }
    }
}