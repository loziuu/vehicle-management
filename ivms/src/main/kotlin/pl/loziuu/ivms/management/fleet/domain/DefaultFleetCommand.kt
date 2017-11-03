package pl.loziuu.ivms.management.fleet.domain

import org.springframework.stereotype.Component
import pl.loziuu.ivms.maintenance.journal.port.JournalCommand
import pl.loziuu.ivms.management.fleet.port.primary.FleetCommand
import pl.loziuu.ivms.management.fleet.port.secondary.FleetRepository
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails

@Component
class DefaultFleetCommand(val repository: FleetRepository, val journalPort: JournalCommand) : FleetCommand {

    override fun addVehicle(fleetId: Long, details: VehicleDetails) {
        val fleet = repository.findOne(fleetId)
        val vehicleId = fleet.addVehicle(details)
        repository.save(fleet)
        journalPort.setupJournal(fleet.getVehicle(vehicleId).id)
    }
}