package pl.loziuu.ivms.maintenance.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutResult
import pl.loziuu.ivms.maintenance.journal.domain.Journal
import pl.loziuu.ivms.maintenance.journal.domain.JournalRepository
import java.time.LocalDate

@SpringBootTest
@RunWith(SpringRunner::class)
class MaintenanceServiceTest {

    @Autowired
    lateinit var service : MaintenanceService

    @Autowired
    lateinit var queryService : MaintenanceQueryService

    @Autowired
    lateinit var repository : JournalRepository

    @Test
    fun registerNewInsuranceShouldInsureVehicle() {
        service.registerInsurance(2L, LocalDate.now(), LocalDate.now().plusYears(1), "PZU")

        assertThat(repository.findOneByVehicleId(2L).hasActualInsurance()).isTrue()
    }

    @Test
    fun registerTwoRepairsAndShouldReturnTheirSum() {
        service.registerRepair(2L, "Some repair", LocalDate.now(), 250.0)
        service.registerRepair(2L, "Some second repair", LocalDate.now(), 250.0)

        assertThat(repository.findOneByVehicleId(2L).sumRepairExpenses()).isEqualTo(500.0)
    }

    @Test
    fun registerPositiveCheckoutShouldReturnViableCheckout() {
        service.registerCheckout(2L, LocalDate.now(), LocalDate.now().plusYears(1), CheckoutResult.POSITIVE)

        assertThat(repository.findOneByVehicleId(2L).hasValidCheckout()).isTrue()
    }

    @Test
    fun getJournalsForFleet() {
        val journals = queryService.getJournalsForFleet(1L)

        assertThat(journals).hasSize(2)
    }
}
