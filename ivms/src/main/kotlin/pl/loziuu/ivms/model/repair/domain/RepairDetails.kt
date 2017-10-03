package pl.loziuu.ivms.model.repair.domain

import java.time.LocalDate
import javax.persistence.Embeddable

@Embeddable
data class RepairDetails(val description: String = "",
                         val cost: Double = 0.0,
                         val date: LocalDate = LocalDate.now())