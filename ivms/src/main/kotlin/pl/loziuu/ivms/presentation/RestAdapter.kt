package pl.loziuu.ivms.presentation

import org.springframework.stereotype.Service
import pl.loziuu.ivms.management.application.ManagementQueryService
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails

@Service
class RestAdapter(val service: ManagementQueryService) {

    fun getFleet(id: Long): List<VehicleResource> {
        return service.getFleet(id).vehicles.map { VehicleResource(it.id, it.details) }
    }

    fun getFleetVehicle(id: Long, vehicleId: Long): VehicleResource {
        val vehicle = service.getFleet(id).getVehicle(vehicleId)
        return VehicleResource(vehicle.id, vehicle.details)
    }
}

class VehicleResource(id: Long = 0, val details: VehicleDetails = VehicleDetails())