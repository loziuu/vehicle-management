package pl.loziuu.ivms.model.repair.domain

class RepairServiceImpl(val repository: RepairRepository) : RepairService {
    override fun add(dto: RepairDto): RepairDto =
        repository.save(dto.toEntity()).toDto()

    override fun delete(repairId: Long) {
        repository.delete(repairId)
    }
}
