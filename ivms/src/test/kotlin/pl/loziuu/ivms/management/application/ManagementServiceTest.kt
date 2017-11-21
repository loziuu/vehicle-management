package pl.loziuu.ivms.management.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import pl.loziuu.ivms.maintenance.journal.domain.JournalRepository
import pl.loziuu.ivms.management.fleet.port.FleetRepository
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails

@SpringBootTest
@RunWith(SpringRunner::class)
class ManagementServiceTest {

    @Autowired
    lateinit var service : ManagementService

    @Autowired
    lateinit var queryService: ManagementQueryService

    @Autowired
    lateinit var repository : FleetRepository

    @Autowired
    lateinit var journalRepository : JournalRepository

    @Test
    fun createFleetShouldReturnId() {
        val id = service.createFleet("New fleet")

        assertThat(repository.findOne(id).name).isEqualTo("New fleet")
    }

    @Test
    fun addVehicleToFleetShouldReturnVehicleIdAndRegisterNewJournal() {
        val fleetId = 2L

        val vehicleId = service.addVehicle(fleetId, VehicleDetails(model = "Test"))

        val vehicle = repository.findOne(fleetId).getVehicle(2L)
        assertThat(vehicle.details.model).isEqualTo("Test")
        assertThat(vehicle.fleetId).isEqualTo(fleetId)
        assertThat(journalRepository.findOneByVehicleId(vehicleId)).isNotNull()
    }

    @Test
    fun removeVehicleFromFleetShouldRemoveVehicleAndJournal() {
        val fleetId = 2L
        val localVehicleId = 1L
        val vehicleId = repository.findOne(fleetId).getVehicle(localVehicleId).id

        service.removeVehicle(fleetId, localVehicleId)

        val fleet = repository.findOne(fleetId)
        assertThat(journalRepository.findOneByVehicleId(vehicleId)).isNull()
    }

    @Test
    fun createFleetAndAddVehicleToItShouldCreateJournal() {
        val fleetId = service.createFleet("Second fleet")

        val vehicleId = service.addVehicle(fleetId, VehicleDetails())

        assertThat(vehicleId).isEqualTo(4L)
        val journal = journalRepository.findOneByVehicleId(vehicleId)
        print(journal.repairs)
        assertThat(journal).isNotNull()
        assertThat(journal.sumRepairExpenses()).isEqualTo(0.0)
    }

    @Test
    fun getFleet() {
        val fleet = queryService.getFleet(1L)

        assertThat(fleet.vehicles).hasSize(2)
    }
}
