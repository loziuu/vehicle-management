package pl.loziuu.ivms.maintenance.repair.query

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RepairQueryRepository : JpaRepository<RepairQueryDto, Long> {
}