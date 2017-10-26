package pl.loziuu.ivms.insurance.domain

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "insurance")
data class Insurance(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                     val id: Long = 0,
                     val dateRange: InsurancePeriod = InsurancePeriod(),
                     val company: Company = Company(),
                     var vehicleId: Long = 0) {

    fun isActual(): Boolean {
        return getExpirationDate().isAfter(LocalDate.now())
    }

    fun getExpirationDate(): LocalDate {
        return dateRange.endDate
    }

    fun getBeginningDate(): LocalDate {
        return dateRange.startDate
    }
}