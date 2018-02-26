package pl.loziuu.ivms.presentation.fleet.requests

import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails

data class CreateVehicleRequest(val registration: String = "",
                                val details: VehicleDetails = VehicleDetails())