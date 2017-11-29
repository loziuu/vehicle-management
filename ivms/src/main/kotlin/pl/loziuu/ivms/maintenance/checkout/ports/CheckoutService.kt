package pl.loziuu.ivms.maintenance.checkout.ports

import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutResult
import java.time.LocalDate

interface CheckoutService {
    fun registerCheckout(vehicleId: Long, checkoutDate: LocalDate, expirationDate: LocalDate, result: CheckoutResult)
}