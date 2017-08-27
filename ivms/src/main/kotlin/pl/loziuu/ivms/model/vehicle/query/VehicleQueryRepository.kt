package pl.loziuu.ivms.model.vehicle.query

import org.springframework.data.jpa.repository.JpaRepository

interface VehicleQueryRepository : JpaRepository<VehicleQueryDto, Long> {

}
