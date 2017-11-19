package pl.loziuu.ivms.presentation

import org.springframework.web.bind.annotation.*
import org.springframework.web.context.annotation.RequestScope
import pl.loziuu.ivms.management.application.ManagementQueryService
import pl.loziuu.ivms.management.vehicle.domain.Vehicle

@RestController
@RequestMapping("fleet")
class FleetController(val adapter : RestAdapter) : BaseMapping() {

    @GetMapping("{id}")
    fun getFleetVehicles(@PathVariable id: Long): List<VehicleResource> {
        return adapter.getFleet(id)
    }

    @GetMapping("{id}/vehicles/{vehicleId}")
    fun getVehicle(@PathVariable id: Long, @PathVariable vehicleId: Long): VehicleResource {
        return adapter.getFleetVehicle(id, vehicleId)
    }
}