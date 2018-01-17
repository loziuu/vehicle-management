package pl.loziuu.ivms.presentation.fleet.requests

import java.time.LocalDate

data class RegisterRepairRequest(val description: String = "",
                            val date: LocalDate = LocalDate.now(),
                            val cost: Double = 0.0)