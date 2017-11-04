package pl.loziuu.ivms.maintenance.insurance.query

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "insurance")
class InsuranceQueryDto(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                        val id: Long = 0,
                        val startDate: LocalDate = LocalDate.now(),
                        val endDate: LocalDate = LocalDate.now(),
                        val company: String = "",
                        @JsonIgnore val journalId: Long = 0) {

    fun isActual(): Boolean {
        return endDate.isAfter(LocalDate.now())
    }
}