package pl.loziuu.ivms.maintenance.application

import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.maintenance.journal.domain.Journal
import pl.loziuu.ivms.maintenance.journal.domain.JournalRepository

@ApplicationService
class MaintenanceQueryService(val fleetResolver: FleetResolver, val repository: JournalRepository) {

    fun getJournalsForFleet(fleetId: Long) : List<Journal> {
        return fleetResolver.getVehicleIdsForFleet(fleetId).map { repository.findOneByVehicleId(it) }
    }
}