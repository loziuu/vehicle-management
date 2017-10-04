package pl.loziuu.ivms.model.insurance.domain

import java.time.LocalDate
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class InsuranceDto(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                        val id: Long = 0,
                        val startDate: LocalDate = LocalDate.now(),
                        val endDate: LocalDate = LocalDate.now(),
                        val company: String = "",
                        val vehicleId: Long = 0)