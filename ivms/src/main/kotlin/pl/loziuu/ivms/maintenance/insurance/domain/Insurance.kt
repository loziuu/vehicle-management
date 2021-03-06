package pl.loziuu.ivms.maintenance.insurance.domain

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "insurance")
data class Insurance(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                     val id: Long = 0,
                     val dateRange: InsurancePeriod = InsurancePeriod(),
                     val company: Company = Company(),
                     var journalId: Long = 0) {

    fun isActual(): Boolean = getExpirationDate().isAfter(LocalDate.now())

    fun getExpirationDate(): LocalDate = dateRange.endDate

    fun getStartDate(): LocalDate = dateRange.startDate

}