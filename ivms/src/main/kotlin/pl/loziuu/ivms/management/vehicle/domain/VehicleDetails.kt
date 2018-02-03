package pl.loziuu.ivms.management.vehicle.domain

import javax.persistence.Embeddable

@Embeddable
data class VehicleDetails(val model: String = "",
                          val manufacturer: String = "",
                          val productionYear: Int = 0)