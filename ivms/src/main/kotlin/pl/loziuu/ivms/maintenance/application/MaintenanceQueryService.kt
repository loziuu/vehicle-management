package pl.loziuu.ivms.maintenance.application

import org.springframework.transaction.annotation.Transactional
import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.maintenance.journal.query.JournalDto
import pl.loziuu.ivms.maintenance.journal.port.JournalQueryRepository

@ApplicationService
class MaintenanceQueryService(val fleetResolver: FleetResolver, val repository: JournalQueryRepository) {

    @Transactional(readOnly = true)
    fun getJournalForVehicle(vehicleId: Long): JournalDto = repository.findOneByVehicleId(vehicleId);


    @Transactional(readOnly = true)
    fun getJournalsForFleet(fleetId: Long): List<JournalDto> =
            fleetResolver.getVehicleIdsForFleet(fleetId).map { repository.findOneByVehicleId(it) }
}
