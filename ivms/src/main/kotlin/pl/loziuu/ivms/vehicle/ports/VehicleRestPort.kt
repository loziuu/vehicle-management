package pl.loziuu.ivms.vehicle.ports

import org.springframework.hateoas.ResourceSupport
import org.springframework.http.ResponseEntity
import pl.loziuu.ivms.insurance.domain.InsuranceDto
import pl.loziuu.ivms.repair.domain.RepairDetails
import pl.loziuu.ivms.vehicle.domain.VehicleDetails

interface VehicleRestPort {

    fun get(id: Long): ResourceSupport
    fun getVehicleRepairs(id: Long): List<ResourceSupport>
    fun getVehicleInsurances(id: Long): List<ResourceSupport>
    fun getAll(): List<ResourceSupport>
    fun postVehicle(details: VehicleDetails): ResponseEntity<Any>
    fun getVehicleRepair(vehicleId: Long, repairId: Long): ResourceSupport
    fun getVehicleInsurance(vehicleId: Long, insuranceId: Long): ResourceSupport
    fun postRepair(vehicleId: Long, details: RepairDetails): ResponseEntity<Any>
    fun postInsurance(vehicleId: Long, dto: InsuranceDto): ResponseEntity<Any>
    fun deleteVehicle(id: Long): ResponseEntity<Any>
    fun deleteVehicleRepair(vehicleId: Long, repairId: Long): ResponseEntity<Any>
    fun deleteVehicleInsurance(vehicleId: Long, insuranceId: Long): ResponseEntity<Any>
}