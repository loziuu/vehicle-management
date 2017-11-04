package pl.loziuu.ivms.acceptanceTests

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pl.loziuu.ivms.maintenance.journal.domain.JournalRepository
import pl.loziuu.ivms.management.fleet.port.primary.FleetCommand
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails

@SpringBootTest
@RunWith(SpringRunner::class)
class CreationalTests {

    @Autowired
    lateinit var service: FleetCommand

    @Autowired
    lateinit var journalRepository: JournalRepository

    @Test
    fun shouldCreateVehicleAndSetupNewJournal() {
        val id = service.addVehicle(1L, VehicleDetails("Model", "Manufacturer", 2000))

        print(journalRepository.findAll())

        assertThat(id).isNotNull()
        assertThat(journalRepository.findAll()).hasSize(3)
    }
}