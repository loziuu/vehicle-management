package pl.loziuu.ivms.management.vehicle.ports.secondary

import org.springframework.data.jpa.repository.JpaRepository
import pl.loziuu.ivms.management.vehicle.domain.Vehicle

interface VehicleRepository : JpaRepository<Vehicle, Long> {
}