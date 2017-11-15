package pl.loziuu.ivms.management.fleet.domain

import org.springframework.stereotype.Component
import pl.loziuu.ivms.maintenance.journal.port.JournalSetupCommand
import pl.loziuu.ivms.management.fleet.port.primary.FleetCommand
import pl.loziuu.ivms.management.fleet.port.secondary.FleetRepository
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails

@Component
class DefaultFleetCommand(val repository: FleetRepository, val journalSetupPort: JournalSetupCommand) : FleetCommand {
    override fun createFleet(name: String): Long {
        return repository.save(Fleet(name = name)).id
    }

    override fun addVehicle(fleetId: Long, details: VehicleDetails): Long {
        val fleet = repository.findOne(fleetId)
        val vehicleId = fleet.addVehicle(details)
        repository.save(fleet)
        val addedVehicleId = fleet.getVehicle(vehicleId).id
        journalSetupPort.setupJournal(addedVehicleId)
        return addedVehicleId
    }

    override fun removeVehicle(fleetId: Long, vehicleId: Long) {
        val fleet = repository.findOne(fleetId)
        fleet.removeVehicle(vehicleId)
        repository.save(fleet)
        journalSetupPort.removeJournal(vehicleId)
    }
}