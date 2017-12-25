package pl.loziuu.ivms.presentation.fleet

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.loziuu.ivms.management.vehicle.query.VehicleDto

@CrossOrigin
@RestController
@RequestMapping("/api/v1/fleets")
class FleetQueryController(val fleetAdapter: RestFleetAdapter) {

    @GetMapping
    fun getAllFleets() : ResponseEntity<Any> {
        return fleetAdapter.getFleets()
    }

    @GetMapping("{id}")
    fun getFleet(@PathVariable id: Long) : ResponseEntity<Any> {
        return fleetAdapter.getFleet(id)
    }

    @GetMapping("{id}/vehicles")
    fun getFleetVehicles(@PathVariable id: Long): List<VehicleResource> {
        return fleetAdapter.getFleetVehicles(id)
    }

    @GetMapping("{id}/vehicles/{vehicleId}")
    fun getVehicle(@PathVariable id: Long, @PathVariable vehicleId: Long): VehicleDto {
        return fleetAdapter.getFleetVehicle(id, vehicleId)
    }
}