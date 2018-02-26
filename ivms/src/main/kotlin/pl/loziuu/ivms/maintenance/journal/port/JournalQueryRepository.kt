package pl.loziuu.ivms.maintenance.journal.port

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.loziuu.ivms.maintenance.journal.query.JournalDto

@Repository
interface JournalQueryRepository : JpaRepository<JournalDto, Long> {
    fun findOneByVehicleId(vehicleId: Long): JournalDto
}