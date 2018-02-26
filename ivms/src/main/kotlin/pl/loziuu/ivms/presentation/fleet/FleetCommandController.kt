package pl.loziuu.ivms.presentation.fleet

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.presentation.fleet.requests.*
import javax.xml.ws.Response

@CrossOrigin
@RestController
@RequestMapping("/api/v1/fleets")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
class FleetCommandController(val command: RestCommandAdapter) {

    @PostMapping
    fun postFleet(@RequestBody request: CreateFleetRequest) = command.createFleet(request)

    @DeleteMapping("{fleetId}")
    fun deleteFleet(@PathVariable fleetId: Long) = command.deleteFleet(fleetId)

    @PostMapping("{fleetId}/vehicles")
    fun postVehicle(@PathVariable fleetId: Long, @RequestBody request: CreateVehicleRequest): ResponseEntity<Any>
            = command.addVehicle(fleetId, request.registration, request.details)


    @PutMapping("/{fleetId}/vehicles/{vehicleId}/change-fleet")
    fun moveVehicle(@PathVariable fleetId: Long,
                    @PathVariable vehicleId: Long,
                    @RequestBody request: MoveVehicle
    ): ResponseEntity<Any> = command.moveVehicle(fleetId, vehicleId, request)

    @DeleteMapping("{fleetId}/vehicles/{vehicleId}")
    fun deleteVehicle(@PathVariable fleetId: Long, @PathVariable vehicleId: Long): ResponseEntity<Any>
            = command.deleteVehicle(fleetId, vehicleId)

    @PostMapping("{fleetId}/vehicles/{localId}/insurances")
    fun postNewInsurance(
            @PathVariable fleetId: Long,
            @PathVariable localId: Long,
            @RequestBody request: RegisterInsuranceRequest
    ): ResponseEntity<Any> = command.registerInsurance(fleetId, localId, request)

    @PostMapping("{fleetId}/vehicles/{localId}/repairs")
    fun postRegister(
            @PathVariable fleetId: Long,
            @PathVariable localId: Long,
            @RequestBody request: RegisterRepairRequest
    ): ResponseEntity<Any> = command.registerRepair(fleetId, localId, request)

    @PostMapping("{fleetId}/vehicles/{localId}/checkouts")
    fun postVehicle(
            @PathVariable fleetId: Long,
            @PathVariable localId: Long,
            @RequestBody request: RegisterCheckoutRequest
    ): ResponseEntity<Any> =
            command.registerCheckout(fleetId, localId, request)


    @DeleteMapping("{fleetId}/vehicles/{localId}/insurances/{id}")
    fun deleteInsurance(
            @PathVariable fleetId: Long,
            @PathVariable localId: Long,
            @PathVariable id: Long
    ): ResponseEntity<Any> = command.deleteInsurance(fleetId, localId, id)

    @DeleteMapping("{fleetId}/vehicles/{localId}/repairs/{id}")
    fun deleteRepair(
            @PathVariable fleetId: Long,
            @PathVariable localId: Long,
            @PathVariable id: Long
    ): ResponseEntity<Any> = command.deleteRepair(fleetId, localId, id)

    @DeleteMapping("{fleetId}/vehicles/{localId}/checkouts/{id}")
    fun deleteCheckout(
            @PathVariable fleetId: Long,
            @PathVariable localId: Long,
            @PathVariable id: Long
    ): ResponseEntity<Any> = command.deleteCheckout(fleetId, localId, id)
}