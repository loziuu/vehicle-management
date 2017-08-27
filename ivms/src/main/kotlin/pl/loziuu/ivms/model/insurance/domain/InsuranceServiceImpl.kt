package pl.loziuu.ivms.model.insurance.domain

class InsuranceServiceImpl(val repository: InsuranceRepository): InsuranceService {
    override fun add(dto: InsuranceDto): InsuranceDto = repository.save(dto.toEntity()).toDto()


}