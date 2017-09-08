package pl.loziuu.ivms.model.repair.query

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "repair")
class RepairQueryDto(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                     val id: Long = 0,
                     val description: String = "",
                     val cost: Double = 0.0,
                     val date: LocalDate = LocalDate.now(),
                     @JsonIgnore val vehicleId: Long = 0)
