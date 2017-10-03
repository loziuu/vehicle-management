package pl.loziuu.ivms.model.insurance

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.loziuu.ivms.infrastructure.exceptions.ValidationException
import pl.loziuu.ivms.model.insurance.domain.Insurance
import pl.loziuu.ivms.model.insurance.domain.InsuranceDto
import pl.loziuu.ivms.model.insurance.domain.InsuranceFactory
import java.time.LocalDate

class InsuranceFactoryTest {

    @Test
    fun validDtoShouldReturnInsurance() {
        val insurance = InsuranceFactory.create(InsuranceDto(company = "Company"))

        assertThat(insurance).isInstanceOf(Insurance::class.javaObjectType)
    }

    @Test(expected = ValidationException::class)
    fun blankCompanyShouldReturnInsurance() {
        val insurance = InsuranceFactory.create(InsuranceDto(company = ""))
    }

    @Test(expected = ValidationException::class)
    fun endDateBeforeStartDateShoudThrowException() {
        InsuranceFactory.create(InsuranceDto(startDate = LocalDate.parse("2000-01-01"), endDate = LocalDate.parse("1970-01-01"), company = "Company"))
    }
}