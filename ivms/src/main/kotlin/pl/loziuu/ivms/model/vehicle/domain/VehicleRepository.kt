package pl.loziuu.ivms.model.vehicle.domain

import org.springframework.data.jpa.repository.JpaRepository

interface VehicleRepository : JpaRepository<Vehicle, Long> {
}