package pl.loziuu.ivms.vehicle.ports.secondary

import org.springframework.data.jpa.repository.JpaRepository
import pl.loziuu.ivms.vehicle.query.VehicleQueryDto

interface VehicleQueryRepository : JpaRepository<VehicleQueryDto, Long> {

}
