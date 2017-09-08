package pl.loziuu.ivms.model.insurance.domain

import java.time.LocalDate
import java.time.LocalDate.now
import javax.persistence.*

@Entity
@Table(name = "insurance")
data class Insurance(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                     val id: Long = 0,
                     val startDate: LocalDate = now(),
                     val endDate: LocalDate = now(),
                     val company: String = "",
                     val vehicleId: Long = 0) {
    fun toDto() = InsuranceDto(this.id, this.startDate, this.endDate, this.company, this.vehicleId)
}