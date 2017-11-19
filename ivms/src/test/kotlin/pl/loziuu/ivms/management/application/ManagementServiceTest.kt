package pl.loziuu.ivms.management.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.transaction.TransactionConfiguration
import pl.loziuu.ivms.maintenance.journal.domain.JournalRepository
import pl.loziuu.ivms.management.fleet.port.secondary.FleetRepository
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import javax.transaction.Transactional

@SpringBootTest
@RunWith(SpringRunner::class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

        assertThat(id).isEqualTo(3L)
        assertThat(repository.findOne(3L).name).isEqualTo("New fleet")
    }

    @Test
    fun addVehicleToFleetShouldReturnVehicleIdAndRegisterNewJournal() {
        val fleetId = 2L

        val vehicleId = service.addVehicle(fleetId, VehicleDetails(model = "Test"))

        val vehicle = repository.findOne(fleetId).getVehicle(2L)
        assertThat(vehicleId).isEqualTo(4L)
        assertThat(vehicle.details.model).isEqualTo("Test")
        assertThat(vehicle.fleetId).isEqualTo(fleetId)
        assertThat(journalRepository.findOneByVehicleId(vehicleId)).isNotNull()
    }

    @Test
    fun removeVehicleFromFleetShouldRemoveVehicleAndJournal() {
        val fleetId = 2L
        val vehicleId = 1L

        service.removeVehicle(fleetId, vehicleId)

        val fleet = repository.findOne(fleetId)
        assertThat(fleet.vehicles).hasSize(0)
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