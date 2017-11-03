package pl.loziuu.ivms.presentation

import org.springframework.hateoas.ResourceSupport
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleSearchPort

@RestController
@RequestMapping("v1/vehicles/search")
class SearchRestController(val searchPort: VehicleSearchPort) {

    @GetMapping("is_insured")
    fun getInsured(): List<ResourceSupport> {
        return searchPort.getInsured()
    }

    @GetMapping("is_uninsured")
    fun getUninsured(): List<ResourceSupport> {
        return searchPort.getUninsured()
    }
}