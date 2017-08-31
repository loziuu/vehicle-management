package pl.loziuu.ivms.model.insurance.domain

interface InsuranceService {
    fun add(dto: InsuranceDto): InsuranceDto
    fun delete(id: Long)
}