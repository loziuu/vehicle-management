package pl.loziuu.ivms.infrastructure.adapters

import org.springframework.hateoas.ResourceSupport
import pl.loziuu.ivms.vehicle.ports.primary.VehicleSearchPort
import pl.loziuu.ivms.vehicle.ports.primary.VehicleQueryService

class VehicleSearchAdapter(val queryService: VehicleQueryService) : VehicleSearchPort {

    override fun getInsured(): List<ResourceSupport> {
        return queryService.getInsured().map { VehicleResource(it) }
    }

    override fun getUninsured(): List<ResourceSupport> {
        return queryService.getUninsured().map { VehicleResource(it) }
    }
}