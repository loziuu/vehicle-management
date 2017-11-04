package pl.loziuu.ivms.maintenance.journal

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutResult
import pl.loziuu.ivms.maintenance.insurance.domain.Company
import pl.loziuu.ivms.maintenance.insurance.domain.InsurancePeriod
import pl.loziuu.ivms.maintenance.journal.domain.Journal
import pl.loziuu.ivms.maintenance.repair.domain.RepairDetails
import java.time.LocalDate

class JournalTest {

    @Test
    fun shouldRegisterRepair() {
        val journal = Journal()

        journal.registerRepair(RepairDetails())

        assertThat(journal.repairs).hasSize(1)
    }

    @Test
    fun shouldRegisterInsurance() {
        val journal = Journal()

        journal.registgerInsurance(InsurancePeriod(LocalDate.now(), LocalDate.now()), Company("ABC"))

        assertThat(journal.insurances).hasSize(1)
    }

    @Test
    fun shouldAddCheckout() {
        val journal = Journal()

        journal.registerCheckout(LocalDate.now(), LocalDate.now(), CheckoutResult.POSITIVE)

        assertThat(journal.checkouts).hasSize(1)
    }

    @Test
    fun shouldSumAllRepairs() {
        val journal = Journal()

        journal.registerRepair(RepairDetails(cost = 250.0))
        journal.registerRepair(RepairDetails(cost = 350.0))

        assertThat(journal.sumRepairExpenses()).isEqualTo(600.0)
    }
}