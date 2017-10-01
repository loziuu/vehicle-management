package pl.loziuu.ivms.model.vehicle.domain

import javax.persistence.Embeddable

@Embeddable
class VehicleDetails(val model: String = "",
                     val manufacturer: String = "",
                     val productionYear: Int = 0)