package pl.loziuu.ivms.model.insurance.query

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "insurance")
class InsuranceQueryDto(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                        val id: Long = 0,
                        val startDate: LocalDate = LocalDate.now(),
                        val endDate: LocalDate = LocalDate.now(),
                        val vehicleId: Long = 0)