package pl.loziuu.ivms.model.insurance.query

import org.springframework.data.jpa.repository.JpaRepository

interface InstanceQueryRepository: JpaRepository<InsuranceQueryDto, Long> {
    fun findByVehicleId(vehicleId: Long): List<InsuranceQueryDto>
}