package pl.loziuu.ivms.maintenance.insurance

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.loziuu.ivms.management.infrastructure.exceptions.ValidationException
import pl.loziuu.ivms.maintenance.insurance.domain.Insurance
import pl.loziuu.ivms.maintenance.insurance.domain.InsuranceDto
import pl.loziuu.ivms.maintenance.insurance.domain.InsuranceFactory
import java.time.LocalDate

class InsuranceFactoryTest {

    @Test
    fun validDtoShouldReturnInsurance() {
        val insurance = InsuranceFactory.create(InsuranceDto(company = "Company"))

        assertThat(insurance).isInstanceOf(Insurance::class.javaObjectType)
    }

    @Test(expected = ValidationException::class)
    fun blankCompanyShouldThrowException() {
        val insurance = InsuranceFactory.create(InsuranceDto(company = ""))
    }

    @Test(expected = ValidationException::class)
    fun endDateBeforeStartDateShoudThrowException() {
        InsuranceFactory.create(InsuranceDto(startDate = LocalDate.parse("2000-01-01"), endDate = LocalDate.parse("1970-01-01"), company = "Company"))
    }
}