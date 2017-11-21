package pl.loziuu.ivms.management.fleet.query

import pl.loziuu.ivms.management.vehicle.query.VehicleDto
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "fleet")
class FleetDto(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String = "",
        @OneToMany(mappedBy = "fleetId", fetch = FetchType.EAGER) val vehicles: MutableSet<VehicleDto> = HashSet()) {

    fun getVehicle(vehicleId: Long): VehicleDto {
        return vehicles.first { it -> it.local == vehicleId }
    }
}