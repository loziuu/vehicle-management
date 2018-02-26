package pl.loziuu.ivms.management.vehicle.domain

import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "vehicle")
class Vehicle(
        @Id @GeneratedValue(strategy = IDENTITY) val id: Long = 0,
        val details: VehicleDetails = VehicleDetails(),
        @Column(unique = true) val registration: String = "",
        var fleetId: Long = 0,
        var local: Long = 0) {

    fun moveToFleet(fleetId: Long) {
        this.fleetId = fleetId
    }
}