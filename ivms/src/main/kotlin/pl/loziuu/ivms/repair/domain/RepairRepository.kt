package pl.loziuu.ivms.repair.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RepairRepository : JpaRepository<Repair, Long> {
}
