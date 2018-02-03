package pl.loziuu.ivms.maintenance.insurance

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.loziuu.ivms.ddd.DomainValidationException
import pl.loziuu.ivms.maintenance.insurance.domain.InsuranceFactory
import java.time.LocalDate

class InsuranceFactoryTest {

    private val companyName = "PZU"

    @Test
    fun insurancePeriodLessThan12MonthsShouldReturnInsurance() {
        val validEndDate = LocalDate.now().plusMonths(10)

        val insurance = InsuranceFactory.create(LocalDate.now(), validEndDate, companyName)

        assertThat(insurance).isNotNull()
    }

    @Test
    fun insurancePeriodExcatly12MontsShouldReturnInsurance() {
        val startDate = LocalDate.now()
        val validEndDate = startDate.plusMonths(12)

        val insurance = InsuranceFactory.create(startDate, validEndDate, companyName)

        assertThat(insurance).isNotNull()
    }

    @Test(expected = DomainValidationException::class)
    fun insurancePeriodLongerThan12MonthsShouldThrowException() {
        val invalidEndDate = LocalDate.now().plusMonths(13)

        InsuranceFactory.create(LocalDate.now(), invalidEndDate, companyName);
    }
}