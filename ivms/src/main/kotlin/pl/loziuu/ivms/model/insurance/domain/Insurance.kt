package pl.loziuu.ivms.model.insurance.domain

import javax.persistence.*

@Entity
@Table(name = "insurance")
data class Insurance(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                     val id: Long = 0,
                     val dateRange: InsurancePeriod = InsurancePeriod(),
                     val company: String = "",
                     var vehicleId: Long = 0)