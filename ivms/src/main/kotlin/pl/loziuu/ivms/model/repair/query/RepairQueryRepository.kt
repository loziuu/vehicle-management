package pl.loziuu.ivms.model.repair.query

import org.springframework.data.jpa.repository.JpaRepository

interface RepairQueryRepository: JpaRepository<RepairQueryDto, Long> {
    fun findByVehicleId(repair: Long): List<RepairQueryDto>
}