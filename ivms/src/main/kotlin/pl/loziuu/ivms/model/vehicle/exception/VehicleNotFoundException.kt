package pl.loziuu.ivms.model.vehicle.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class VehicleNotFoundException : Exception() {
    override val message: String? = "Can't find Vehicle for given id."
}