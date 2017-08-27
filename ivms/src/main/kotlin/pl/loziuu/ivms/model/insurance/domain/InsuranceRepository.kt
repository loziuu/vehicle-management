package pl.loziuu.ivms.model.insurance.domain

import org.springframework.data.jpa.repository.JpaRepository

interface InsuranceRepository : JpaRepository<Insurance, Long> {
    fun findByVehicleId(vehicleId: Long): List<Insurance>
}