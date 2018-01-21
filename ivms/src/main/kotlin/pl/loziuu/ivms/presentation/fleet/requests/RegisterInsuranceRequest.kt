package pl.loziuu.ivms.presentation.fleet.requests

import java.time.LocalDate

class RegisterInsuranceRequest(val startDate: LocalDate = LocalDate.now(),
                               val endDate: LocalDate = LocalDate.now(),
                               val company: String = "")