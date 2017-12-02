package pl.loziuu.ivms.presentation.dtos

import java.time.LocalDate

class RegisterInsuranceDto(val startDate: LocalDate = LocalDate.now(),
                           val endDate: LocalDate = LocalDate.now(),
                           val company: String = "")