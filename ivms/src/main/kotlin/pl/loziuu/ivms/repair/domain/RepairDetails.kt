package pl.loziuu.ivms.repair.domain

import java.time.LocalDate
import javax.persistence.Embeddable

@Embeddable
data class RepairDetails(val description: String = "",
                         val cost: Double = 0.0,
                         val date: LocalDate = LocalDate.now())