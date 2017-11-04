package pl.loziuu.ivms.maintenance.journal.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JournalRepository : JpaRepository<Journal, Long> {
    fun findOneByVehicleId(vehicleId: Long) : Journal
    fun deleteByVehicleId(vehicleId: Long)
}