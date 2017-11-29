package pl.loziuu.ivms.management.fleet.query

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FleetQueryRepository : JpaRepository<FleetDto, Long> {

}