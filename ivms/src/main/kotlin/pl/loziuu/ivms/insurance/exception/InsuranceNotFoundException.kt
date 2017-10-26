package pl.loziuu.ivms.insurance.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class InsuranceNotFoundException : Exception() {
    override val message: String? = "Can't find Insurance for given vehicle or Insurance does not exist."
}