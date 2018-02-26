package pl.loziuu.ivms.management.fleet.port

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.loziuu.ivms.management.fleet.query.FleetDto

@Repository
interface FleetQueryRepository : JpaRepository<FleetDto, Long>