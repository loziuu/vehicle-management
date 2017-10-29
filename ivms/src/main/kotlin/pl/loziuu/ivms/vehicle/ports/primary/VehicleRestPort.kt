package pl.loziuu.ivms.vehicle.ports.primary

import org.springframework.hateoas.ResourceSupport
import org.springframework.http.ResponseEntity
import pl.loziuu.ivms.insurance.domain.InsuranceDto
import pl.loziuu.ivms.repair.domain.RepairDetails
import pl.loziuu.ivms.vehicle.domain.VehicleDetails

interface VehicleRestPort {

    fun getVehicle(id: Long): ResourceSupport
    fun getAllVehicles(): List<ResourceSupport>
    fun getVehicleRepairs(id: Long): List<ResourceSupport>
    fun getVehicleInsurances(id: Long): List<ResourceSupport>
    fun getVehicleRepair(vehicleId: Long, repairId: Long): ResourceSupport
    fun getVehicleInsurance(vehicleId: Long, insuranceId: Long): ResourceSupport
    fun getVehicleCheckouts(vehicleId: Long): List<ResourceSupport>
    fun getVehicleCheckout(vehicleId: Long, id: Long): ResourceSupport

    fun postVehicle(details: VehicleDetails): ResponseEntity<Any>
    fun postRepair(vehicleId: Long, details: RepairDetails): ResponseEntity<Any>
    fun postInsurance(vehicleId: Long, dto: InsuranceDto): ResponseEntity<Any>
    fun deleteVehicle(id: Long): ResponseEntity<Any>
    fun deleteVehicleRepair(vehicleId: Long, repairId: Long): ResponseEntity<Any>
    fun deleteVehicleInsurance(vehicleId: Long, insuranceId: Long): ResponseEntity<Any>
}