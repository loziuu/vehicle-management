package pl.loziuu.ivms.model.repair.domain

class RepairServiceImpl(val repository: RepairRepository) : RepairService {
    override fun add(dto: RepairDto) {
        repository.save(dto.toEntity()).toDto()
    }
}
