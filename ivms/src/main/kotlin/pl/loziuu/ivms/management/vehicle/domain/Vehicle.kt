package pl.loziuu.ivms.management.vehicle.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "vehicle")
class Vehicle(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY) val id: Long = 0,
        val details: VehicleDetails = VehicleDetails(),
        var fleetId: Long = 0,
        var local: Long = 0) {

    fun moveToFleet(fleetId: Long) {
        this.fleetId = fleetId
    }
}