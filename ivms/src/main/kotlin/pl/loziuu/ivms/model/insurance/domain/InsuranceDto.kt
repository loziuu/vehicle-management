package pl.loziuu.ivms.model.insurance.domain

import org.springframework.cglib.core.EmitUtils
import java.time.LocalDate
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class InsuranceDto (@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
    val vehicleId: Long = 0) {
    fun toEntity(): Insurance = Insurance(this.id, this.startDate, this.endDate, this.vehicleId)
}