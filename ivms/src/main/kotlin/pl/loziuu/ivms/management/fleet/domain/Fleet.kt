package pl.loziuu.ivms.management.fleet.domain

import pl.loziuu.ivms.ddd.Aggregate
import pl.loziuu.ivms.management.vehicle.domain.Vehicle
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import java.util.*
import javax.persistence.*

@Entity
@Aggregate
@Table(name = "fleet")
class Fleet(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val name: String = "",
        @OneToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "fleetId") val vehicles: MutableSet<Vehicle> = HashSet()) {

    fun addVehicle(details: VehicleDetails): Long {
        val localId = vehicles.size+1.toLong()
        vehicles.add(Vehicle(0, details, id, localId))
        return localId
    }

    fun getVehicle(vehicleId: Long): Vehicle {
        return vehicles.first { it -> it.local == vehicleId }
    }

    fun removeVehicle(vehicleId: Long) {
        vehicles.removeIf({ it -> it.local == vehicleId })
    }
}