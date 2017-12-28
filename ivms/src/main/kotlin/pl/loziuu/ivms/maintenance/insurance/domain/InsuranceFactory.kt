package pl.loziuu.ivms.maintenance.insurance.domain

import pl.loziuu.ivms.ddd.DomainValidationException
import java.time.LocalDate

object InsuranceFactory {

    fun create(start: LocalDate, end: LocalDate, companyName: String): Insurance {
        val insurance = Insurance(dateRange = InsurancePeriod(start, end), company = Company(companyName))
        validate(insurance)
        return insurance
    }

    private fun validate(insurance: Insurance) {
        if (insurance.company.name.isBlank())
            throw DomainValidationException("Company name can't be empty")
        if (insurance.getBeginningDate().isAfter(insurance.getExpirationDate()))
            throw DomainValidationException("Start date cannot be after end date")
    }
}
