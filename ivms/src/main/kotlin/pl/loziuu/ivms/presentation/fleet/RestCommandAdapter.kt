package pl.loziuu.ivms.presentation.fleet

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import pl.loziuu.ivms.maintenance.checkout.ports.CheckoutService
import pl.loziuu.ivms.maintenance.insurance.ports.InsuranceService
import pl.loziuu.ivms.maintenance.repair.ports.RepairService
import pl.loziuu.ivms.management.application.ManagementQueryService
import pl.loziuu.ivms.management.application.ManagementService
import pl.loziuu.ivms.management.fleet.port.FleetService
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.management.vehicle.query.VehicleDto
import pl.loziuu.ivms.presentation.RestResponse
import pl.loziuu.ivms.presentation.fleet.requests.*

@Component
class RestCommandAdapter(val managementService: ManagementService,
                         val managementQueryService: ManagementQueryService,
                         val insuranceService: InsuranceService,
                         val repairService: RepairService,
                         var checkoutService: CheckoutService) {

    fun createFleet(request: CreateFleetRequest): ResponseEntity<Any> {
        managementService.createFleet(request.name)
        return RestResponse("Fleet created").toResponseEntity()
    }

    fun addVehicle(fleetId: Long, registration: String, dto: VehicleDetails): ResponseEntity<Any> {
        managementService.addVehicle(fleetId, registration, dto)
        return RestResponse("Vehicle added").toResponseEntity()
    }

    fun registerInsurance(fleetId: Long, vehicleId: Long, request: RegisterInsuranceRequest): ResponseEntity<Any> {
        val vehicle = getVehicleFromFleetByLocalId(fleetId, vehicleId)
        insuranceService.registerInsurance(vehicle.id, request.startDate, request.endDate, request.company)
        return RestResponse("Insurance registered").toResponseEntity()
    }

    fun registerCheckout(fleetId: Long, vehicleId: Long, request: RegisterCheckoutRequest): ResponseEntity<Any> {
        val vehicle = getVehicleFromFleetByLocalId(fleetId, vehicleId)
        checkoutService.registerCheckout(vehicle.id, request.date, request.expirationDate, request.result)
        return RestResponse("Checkout registered").toResponseEntity()
    }

    fun registerRepair(fleetId: Long, vehicleId: Long, request: RegisterRepairRequest): ResponseEntity<Any> {
        val vehicle = getVehicleFromFleetByLocalId(fleetId, vehicleId)
        repairService.registerRepair(vehicle.id, request.description, request.date, request.cost)
        return RestResponse("Repair registered").toResponseEntity()
    }

    private fun getVehicleFromFleetByLocalId(fleetId: Long, vehicleId: Long): VehicleDto {
        val vehicle = managementQueryService.getFleet(fleetId).getVehicle(vehicleId)
        return vehicle
    }

    fun deleteVehicle(fleetId: Long, vehicleId: Long): ResponseEntity<Any> {
        managementService.removeVehicle(fleetId, vehicleId)
        return RestResponse("Vehicle deleted").toResponseEntity()
    }

    fun deleteInsurance(fleetId: Long, vehicleLocalId: Long, insuranceId: Long): ResponseEntity<Any> {
        val vehicle = getVehicleFromFleetByLocalId(fleetId, vehicleLocalId)
        insuranceService.removeInsurance(vehicle.id, insuranceId)
        return RestResponse("Insurance deleted").toResponseEntity()
    }

    fun deleteRepair(fleetId: Long, localId: Long, id: Long): ResponseEntity<Any> {
        val vehicle = getVehicleFromFleetByLocalId(fleetId, localId)
        repairService.removeRepair(vehicle.id, id)
        return RestResponse("Repair deleted").toResponseEntity()
    }

    fun deleteCheckout(fleetId: Long, localId: Long, id: Long): ResponseEntity<Any> {
        val vehicle = getVehicleFromFleetByLocalId(fleetId, localId)
        checkoutService.removeCheckout(vehicle.id, id)
        return RestResponse("Checkout deleted").toResponseEntity()
    }

    fun deleteFleet(fleetId: Long): Any {
        managementService.removeFleet(fleetId)
        return RestResponse("Fleet deleted").toResponseEntity()
    }

    fun moveVehicle(fleetId: Long, vehicleId: Long, request: MoveVehicle): ResponseEntity<Any> {
        managementService.moveVehicleToFleet(fleetId, vehicleId, request.fleetId)
        return RestResponse("Vehicle moved").toResponseEntity()
    }
}
