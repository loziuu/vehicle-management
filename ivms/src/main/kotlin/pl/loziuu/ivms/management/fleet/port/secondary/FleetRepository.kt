package pl.loziuu.ivms.management.fleet.port.secondary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.loziuu.ivms.management.fleet.domain.Fleet

@Repository
interface FleetRepository : JpaRepository<Fleet, Long>