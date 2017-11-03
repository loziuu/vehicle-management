package pl.loziuu.ivms.management.vehicle.ports.secondary

import org.springframework.data.jpa.repository.JpaRepository
import pl.loziuu.ivms.management.vehicle.query.VehicleQueryDto

interface VehicleQueryRepository : JpaRepository<VehicleQueryDto, Long> {

}
