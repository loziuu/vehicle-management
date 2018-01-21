package pl.loziuu.ivms.presentation.fleet

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import pl.loziuu.ivms.maintenance.checkout.ports.CheckoutService
import pl.loziuu.ivms.maintenance.insurance.ports.InsuranceService
import pl.loziuu.ivms.maintenance.repair.ports.RepairService
import pl.loziuu.ivms.management.application.ManagementQueryService
import pl.loziuu.ivms.management.fleet.port.FleetCommand
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.management.vehicle.query.VehicleDto
import pl.loziuu.ivms.presentation.RestResponse
import pl.loziuu.ivms.presentation.fleet.requests.CreateFleetRequest
import pl.loziuu.ivms.presentation.fleet.requests.RegisterCheckoutRequest
import pl.loziuu.ivms.presentation.fleet.requests.RegisterInsuranceRequest
import pl.loziuu.ivms.presentation.fleet.requests.RegisterRepairRequest

@Component
class RestCommandAdapter(val fleetCommand: FleetCommand,
                         val managementQueryService: ManagementQueryService,
                         val insuranceService: InsuranceService,
                         val repairService: RepairService,
                         var checkoutService: CheckoutService) {

    fun createFleet(request: CreateFleetRequest): ResponseEntity<Any> {
        fleetCommand.createFleet(name = request.name)
        return RestResponse("Fleet created").toResponseEntity()
    }

    fun addVehicle(fleetId: Long, dto: VehicleDetails): ResponseEntity<Any> {
        fleetCommand.createVehicle(fleetId, dto)
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
}
