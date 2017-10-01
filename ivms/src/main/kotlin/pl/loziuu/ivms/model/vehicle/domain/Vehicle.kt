package pl.loziuu.ivms.model.vehicle.domain

import javax.persistence.GeneratedValue
import javax.persistence.Id

@javax.persistence.Entity
@javax.persistence.Table(name = "vehicle")
data class Vehicle(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY) val id: Long = 0,
        val details: VehicleDetails = VehicleDetails())