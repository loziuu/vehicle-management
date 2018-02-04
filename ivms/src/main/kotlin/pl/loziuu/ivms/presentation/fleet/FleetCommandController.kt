package pl.loziuu.ivms.presentation.fleet

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.presentation.fleet.requests.CreateFleetRequest
import pl.loziuu.ivms.presentation.fleet.requests.RegisterCheckoutRequest
import pl.loziuu.ivms.presentation.fleet.requests.RegisterInsuranceRequest
import pl.loziuu.ivms.presentation.fleet.requests.RegisterRepairRequest

@CrossOrigin
@RestController
@RequestMapping("/api/v1/fleets")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
class FleetCommandController(val command: RestCommandAdapter) {

    @PostMapping
    fun postFleet(@RequestBody request: CreateFleetRequest) = command.createFleet(request)

    @PostMapping("{fleetId}/vehicles")
    fun postVehicle(@PathVariable fleetId: Long, @RequestBody dto: VehicleDetails): ResponseEntity<Any>
            = command.addVehicle(fleetId, dto)

    @PostMapping("{fleetId}/vehicles/{localId}/insurances")
    fun postNewInsurance(
            @PathVariable fleetId: Long,
            @PathVariable localId: Long,
            @RequestBody request: RegisterInsuranceRequest
    ): ResponseEntity<Any> = command.registerInsurance(fleetId, localId, request)

    @PostMapping("{fleetId}/vehicles/{localId}/repairs")
    fun postVehicle(
            @PathVariable fleetId: Long,
            @PathVariable localId: Long,
            @RequestBody request: RegisterRepairRequest
    ): ResponseEntity<Any> =
            command.registerRepair(fleetId, localId, request)

    @PostMapping("{fleetId}/vehicles/{localId}/checkouts")
    fun postVehicle(
            @PathVariable fleetId: Long,
            @PathVariable localId: Long,
            @RequestBody request: RegisterCheckoutRequest
    ): ResponseEntity<Any> =
            command.registerCheckout(fleetId, localId, request)
}