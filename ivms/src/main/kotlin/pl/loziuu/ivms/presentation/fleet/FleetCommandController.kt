package pl.loziuu.ivms.presentation.fleet

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.presentation.dtos.CreateFleetDto
import pl.loziuu.ivms.presentation.dtos.RegisterCheckoutDto
import pl.loziuu.ivms.presentation.dtos.RegisterInsuranceDto
import pl.loziuu.ivms.presentation.dtos.RegisterRepairDto

@CrossOrigin
@RestController
@RequestMapping("/api/v1/fleets")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
class FleetCommandController(val command: RestCommandAdapter) {

    @PostMapping
    fun postFleet(@RequestBody dto: CreateFleetDto)
            = command.createFleet(dto)

    @PostMapping("{fleetId}/vehicles")
    fun postVehicle(@PathVariable fleetId: Long, @RequestBody dto: VehicleDetails)
            = command.addVehicle(fleetId, dto)

    @PostMapping("{fleetId}/vehicles/{localId}/insurances")
    fun postNewInsurance(@PathVariable fleetId: Long, @PathVariable localId: Long, @RequestBody dto: RegisterInsuranceDto)
            = command.registerInsurance(fleetId, localId, dto)

    @PostMapping("{fleetId}/vehicles/{localId}/repairs")
    fun postVehicle(@PathVariable fleetId: Long, @PathVariable localId: Long, @RequestBody dto: RegisterRepairDto)
            = command.registerRepair(fleetId, localId, dto)

    @PostMapping("{fleetId}/vehicles/{localId}/checkouts")
    fun postVehicle(@PathVariable fleetId: Long, @PathVariable localId: Long, @RequestBody dto: RegisterCheckoutDto)
            = command.registerCheckout(fleetId, localId, dto)
}