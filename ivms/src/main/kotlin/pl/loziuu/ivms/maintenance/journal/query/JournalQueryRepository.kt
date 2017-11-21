package pl.loziuu.ivms.maintenance.journal.query

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JournalQueryRepository : JpaRepository<JournalDto, Long> {
    fun findOneByVehicleId(vehicleId: Long): JournalDto
}