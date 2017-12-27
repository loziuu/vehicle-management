package pl.loziuu.ivms.presentation.fleet

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import pl.loziuu.ivms.maintenance.checkout.ports.CheckoutService
import pl.loziuu.ivms.maintenance.insurance.ports.InsuranceService
import pl.loziuu.ivms.maintenance.repair.ports.primary.RepairService
import pl.loziuu.ivms.management.application.ManagementQueryService
import pl.loziuu.ivms.management.fleet.port.FleetCommand
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.management.vehicle.query.VehicleDto
import pl.loziuu.ivms.presentation.RestResponse
import pl.loziuu.ivms.presentation.dtos.CreateFleetDto
import pl.loziuu.ivms.presentation.dtos.RegisterCheckoutDto
import pl.loziuu.ivms.presentation.dtos.RegisterInsuranceDto
import pl.loziuu.ivms.presentation.dtos.RegisterRepairDto

@Component
class RestCommandAdapter(val fleetCommand: FleetCommand,
                         val managementQueryService: ManagementQueryService,
                         val insuranceService: InsuranceService,
                         val repairService: RepairService,
                         var checkoutService: CheckoutService) {

    fun createFleet(dto: CreateFleetDto): ResponseEntity<Any> {
        fleetCommand.createFleet(name = dto.name)
        return RestResponse("Fleet created").toResponseEntity()
    }

    fun addVehicle(fleetId: Long, dto: VehicleDetails): ResponseEntity<Any> {
        fleetCommand.createVehicle(fleetId, dto)
        return RestResponse("Vehicle added").toResponseEntity()
    }

    fun registerInsurance(fleetId: Long, vehicleId: Long, dto: RegisterInsuranceDto): ResponseEntity<Any> {
        val vehicle = getVehicleFromFleetByLocalId(fleetId, vehicleId)
        insuranceService.registerInsurance(vehicle.id, dto.startDate, dto.endDate, dto.company)
        return RestResponse("Insurance registered").toResponseEntity()
    }

    fun registerCheckout(fleetId: Long, vehicleId: Long, dto: RegisterCheckoutDto): ResponseEntity<Any> {
        val vehicle = getVehicleFromFleetByLocalId(fleetId, vehicleId)
        checkoutService.registerCheckout(vehicle.id, dto.date, dto.expirationDate, dto.result)
        return RestResponse("Checkout registered").toResponseEntity()
    }

    fun registerRepair(fleetId: Long, vehicleId: Long, dto: RegisterRepairDto): ResponseEntity<Any> {
        val vehicle = getVehicleFromFleetByLocalId(fleetId, vehicleId)
        repairService.registerRepair(vehicle.id, dto.description, dto.date, dto.cost)
        return RestResponse("Repair registered").toResponseEntity()
    }

    private fun getVehicleFromFleetByLocalId(fleetId: Long, vehicleId: Long): VehicleDto {
        val vehicle = managementQueryService.getFleet(fleetId).getVehicle(vehicleId)
        return vehicle
    }
}
