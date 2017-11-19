package pl.loziuu.ivms.management.application

import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.management.fleet.domain.Fleet
import pl.loziuu.ivms.management.fleet.port.secondary.FleetRepository

@ApplicationService
class ManagementQueryService(val repository: FleetRepository) {

    fun getFleet(fleetId: Long): Fleet {
        return repository.findOne(fleetId)
    }
}