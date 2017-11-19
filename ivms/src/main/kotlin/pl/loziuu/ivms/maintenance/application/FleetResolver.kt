package pl.loziuu.ivms.maintenance.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pl.loziuu.ivms.management.application.ManagementQueryService

@Component
class FleetResolver(@Autowired val fleetService: ManagementQueryService) {

    fun getVehicleIdsForFleet(fleetId: Long): List<Long> {
        return fleetService.getFleet(fleetId).vehicles.map { it.id }
    }
}