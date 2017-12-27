package pl.loziuu.ivms.maintenance.journal

import org.apache.tomcat.jni.Local
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.loziuu.ivms.maintenance.checkout.domain.Checkout
import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutResult
import pl.loziuu.ivms.maintenance.insurance.domain.Company
import pl.loziuu.ivms.maintenance.insurance.domain.Insurance
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

        journal.registerInsurance(Insurance(
                dateRange = InsurancePeriod(LocalDate.now(), LocalDate.now()),
                company = Company("ABC")))

        assertThat(journal.insurances).hasSize(1)
    }

    @Test
    fun shouldAddCheckout() {
        val journal = Journal()

        journal.registerCheckout(Checkout())

        assertThat(journal.checkouts).hasSize(1)
    }

    @Test
    fun shouldSumAllRepairs() {
        val journal = Journal()

        journal.registerRepair(RepairDetails(cost = 250.0))
        journal.registerRepair(RepairDetails(cost = 350.0))

        assertThat(journal.sumRepairExpenses()).isEqualTo(600.0)
    }

    @Test
    fun journalWithoutInsuranceShouldNotBeInsured() {
        assertThat(Journal().hasActualInsurance()).isFalse()
    }

    @Test
    fun journalWithNewInsuranceShouldBeInsured() {
        val journal = Journal()

        journal.registerInsurance(Insurance())

        assertThat(journal.hasActualInsurance()).isTrue()
    }

    @Test
    fun journalWithOldInsuranceShouldNotBeInsured() {
        val journal = Journal()

        journal.registerInsurance(Insurance(
                dateRange = InsurancePeriod(date("2000-01-01"), date("2001-01-01")),
                company = Company("PZU")))

        assertThat(journal.hasActualInsurance()).isFalse()
    }

    @Test
    fun journalWithoutCheckoutShouldNotContainValidCheckout() {
        val journal = Journal()

        assertThat(journal.hasValidCheckout()).isFalse()
    }

    @Test
    fun addPositiveCheckoutShouldContaintValidCheckout() {
        val journal = Journal()

        journal.registerCheckout(Checkout(
                date = LocalDate.now(),
                expirationDate = LocalDate.now().plusDays(1),
                result = CheckoutResult.POSITIVE))

        assertThat(journal.hasValidCheckout()).isTrue()
    }

    @Test
    fun addNegativeCheckoutShouldNotContainValidCheckout() {
        val journal = Journal()

        journal.registerCheckout(Checkout())

        assertThat(journal.hasValidCheckout()).isFalse()
    }

    private fun date(date: String) = LocalDate.parse(date)
}