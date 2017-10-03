package pl.loziuu.ivms.model.insurance.domain

import pl.loziuu.ivms.infrastructure.exceptions.ValidationException
    
object InsuranceFactory {

    fun create(dto: InsuranceDto): Insurance {
        if (dto.company.isBlank() || dto.startDate.isAfter(dto.endDate))
            throw ValidationException()
        return Insurance(0, InsurancePeriod(dto.startDate, dto.endDate), dto.company)
    }
}
