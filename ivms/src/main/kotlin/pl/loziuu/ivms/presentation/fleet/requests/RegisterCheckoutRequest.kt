package pl.loziuu.ivms.presentation.fleet.requests

import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutResult
import java.time.LocalDate

data class RegisterCheckoutRequest(val date: LocalDate = LocalDate.now(),
                              val expirationDate: LocalDate = LocalDate.now(),
                              val result: CheckoutResult = CheckoutResult.NEGATIVE)