package pl.loziuu.ivms.presentation.adapters

import org.springframework.stereotype.Component
import pl.loziuu.ivms.maintenance.checkout.ports.CheckoutService
import pl.loziuu.ivms.maintenance.insurance.ports.InsuranceService
import pl.loziuu.ivms.maintenance.repair.ports.primary.RepairService
import pl.loziuu.ivms.management.application.ManagementQueryService
import pl.loziuu.ivms.management.fleet.port.FleetCommand
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.management.vehicle.query.VehicleDto
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

    fun createFleet(dto: CreateFleetDto) {
        fleetCommand.createFleet(name = dto.name)
    }

    fun addVehicle(fleetId: Long, dto: VehicleDetails) {
        fleetCommand.createVehicle(fleetId, dto)
    }

    fun registerInsurance(fleetId: Long, vehicleId: Long, dto: RegisterInsuranceDto) {
        val vehicle = getVehicleFromFleetByLocalId(fleetId, vehicleId)
        insuranceService.registerInsurance(vehicle.id, dto.startDate, dto.endDate, dto.companyName)
    }

    fun registerCheckout(fleetId: Long, vehicleId: Long, dto: RegisterCheckoutDto) {
        val vehicle = getVehicleFromFleetByLocalId(fleetId, vehicleId)
        checkoutService.registerCheckout(vehicle.id, dto.date, dto.expirationDate, dto.result)
    }

    fun registerRepair(fleetId: Long, vehicleId: Long, dto: RegisterRepairDto) {
        val vehicle = getVehicleFromFleetByLocalId(fleetId, vehicleId)
        repairService.registerRepair(vehicle.id, dto.description, dto.date, dto.cost)
    }

    private fun getVehicleFromFleetByLocalId(fleetId: Long, vehicleId: Long): VehicleDto {
        val vehicle = managementQueryService.getFleet(fleetId).getVehicle(vehicleId)
        return vehicle
    }
}
