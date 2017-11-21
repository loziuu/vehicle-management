package pl.loziuu.ivms.presentation.dtos

import java.time.LocalDate

class RegisterRepairDto(val description: String = "",
                        val date: LocalDate = LocalDate.now(),
                        val cost: Double = 0.0) {
}