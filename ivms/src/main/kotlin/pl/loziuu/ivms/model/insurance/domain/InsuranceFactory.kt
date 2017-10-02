package pl.loziuu.ivms.model.insurance.domain

object InsuranceFactory {

    fun create(dto: InsuranceDto): Insurance {
        return Insurance(0, InsurancePeriod(dto.startDate, dto.endDate), dto.company)
    }
}