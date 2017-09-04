package pl.loziuu.ivms.model.repair.domain

interface RepairService {
    fun add(dto: RepairDto): RepairDto
}
