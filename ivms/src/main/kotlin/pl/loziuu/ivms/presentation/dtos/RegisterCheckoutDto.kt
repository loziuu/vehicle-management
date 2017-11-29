package pl.loziuu.ivms.presentation.dtos

import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutResult
import java.time.LocalDate

class RegisterCheckoutDto(val date: LocalDate = LocalDate.now(),
                          val expirationDate: LocalDate = LocalDate.now(),
                          val result: CheckoutResult = CheckoutResult.NEGATIVE) {
}