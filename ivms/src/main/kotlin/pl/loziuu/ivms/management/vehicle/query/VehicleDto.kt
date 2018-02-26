package pl.loziuu.ivms.management.vehicle.query

import com.fasterxml.jackson.annotation.JsonIgnore
import pl.loziuu.ivms.maintenance.journal.query.JournalDto
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "vehicle")
class VehicleDto(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore val id: Long = 0,
        val model: String = "",
        val manufacturer: String = "",
        val productionYear: Int = 0,
        val registration: String = "",
        @JsonIgnore val fleetId: Long = 0,
        @JsonIgnore val local: Long = 0,
        @OneToOne
        @JoinColumn(name = "id", referencedColumnName = "vehicleId",
                insertable = false, updatable = false)
        val journal: JournalDto = JournalDto())
