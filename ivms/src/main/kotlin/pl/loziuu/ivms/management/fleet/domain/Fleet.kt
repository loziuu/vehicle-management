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

    fun addVehicle(vehicle: Vehicle): Long {
        val localId = vehicles.size+1.toLong()
        vehicle.local = localId
        vehicle.fleetId = id
        vehicles.add(vehicle)
        return localId
    }

    fun getVehicle(vehicleId: Long): Vehicle = vehicles.first { it -> it.local == vehicleId }

    fun removeVehicle(vehicleId: Long): Long {
        val vehicle = getVehicle(vehicleId);
        vehicles.remove(vehicle)
        return vehicle.id
    }
}
