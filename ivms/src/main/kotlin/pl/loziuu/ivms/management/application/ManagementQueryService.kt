package pl.loziuu.ivms.management.application

import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.management.fleet.query.FleetDto
import pl.loziuu.ivms.management.fleet.query.FleetQueryRepository

@ApplicationService
class ManagementQueryService(val repository: FleetQueryRepository) {

    fun getAllFleets(): List<FleetDto> = repository.findAll()

    fun getFleet(fleetId: Long): FleetDto {
        val fleet = repository.findOne(fleetId)
        if (fleet == null)
            throw NoSuchElementException()
        return fleet
    }
}