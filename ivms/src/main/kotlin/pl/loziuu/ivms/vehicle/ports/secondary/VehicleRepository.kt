package pl.loziuu.ivms.vehicle.ports.secondary

import org.springframework.data.jpa.repository.JpaRepository
import pl.loziuu.ivms.vehicle.domain.Vehicle

interface VehicleRepository : JpaRepository<Vehicle, Long> {
}