package pl.loziuu.ivms.vehicle.domain

import pl.loziuu.ivms.infrastructure.exceptions.ValidationException
import java.time.LocalDate

object VehicleFactory {
    fun create(details: VehicleDetails): Vehicle {
        validate(details)
        return Vehicle(details = details)
    }

    private fun validate(details: VehicleDetails) {
        if (details.model.isBlank() || details.manufacturer.isBlank() || details.productionYear !in 1950..LocalDate.now().year)
            throw ValidationException()
    }
}
