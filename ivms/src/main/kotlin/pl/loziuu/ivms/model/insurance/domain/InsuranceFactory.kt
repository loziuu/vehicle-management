package pl.loziuu.ivms.model.insurance.domain

import pl.loziuu.ivms.infrastructure.exceptions.ValidationException

object InsuranceFactory {

    fun create(dto: InsuranceDto): Insurance {
        validate(dto)
        return Insurance(0, InsurancePeriod(dto.startDate, dto.endDate), dto.company)
    }

    private fun validate(dto: InsuranceDto) {
        if (dto.company.isBlank() || dto.startDate.isAfter(dto.endDate))
            throw ValidationException()
    }
}
