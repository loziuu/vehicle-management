package pl.loziuu.ivms.management.fleet.domain

import org.springframework.stereotype.Component
import pl.loziuu.ivms.maintenance.journal.port.JournalSetupCommand
import pl.loziuu.ivms.management.fleet.port.FleetCommand
import pl.loziuu.ivms.management.fleet.port.FleetRepository
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.management.vehicle.domain.VehicleFactory
import javax.transaction.Transactional

@Component
class DefaultFleetCommand(val repository: FleetRepository, val journalSetupPort: JournalSetupCommand) : FleetCommand {
    override fun createFleet(name: String): Long {
        val fleet = FleetFactory.create(name)
        return repository.save(fleet).id
    }

    override fun createVehicle(fleetId: Long, details: VehicleDetails): Long {
        val fleet = repository.findOne(fleetId)
        val vehicleId = fleet.addVehicle(VehicleFactory.create(details))
        repository.save(fleet)

        val addedVehicleId = fleet.getVehicle(vehicleId).id
        journalSetupPort.setupJournal(addedVehicleId)
        return addedVehicleId
    }

    @Transactional
    override fun removeVehicle(fleetId: Long, vehicleId: Long) {
        val fleet = repository.findOne(fleetId)
        journalSetupPort.removeJournal(fleet.getVehicle(vehicleId).id)
        fleet.removeVehicle(vehicleId)
        repository.save(fleet)
    }
}
