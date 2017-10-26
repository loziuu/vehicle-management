package pl.loziuu.ivms.insurance.domain

import java.time.LocalDate
import javax.persistence.Embeddable

@Embeddable
data class InsurancePeriod(val startDate: LocalDate = LocalDate.now(),
                           val endDate: LocalDate = LocalDate.now())