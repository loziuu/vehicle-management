package pl.loziuu.ivms.maintenance.checkout.domain

import pl.loziuu.ivms.ddd.DomainValidationException
import java.time.LocalDate

object CheckoutFactory {
    fun create(checkoutDate: LocalDate, expirationDate: LocalDate, result: CheckoutResult) : Checkout {
        if (checkoutDate.isAfter(expirationDate)) {
            throw DomainValidationException("Checkout date cannot be after expiration date.")
        }
        return Checkout(date = checkoutDate, expirationDate = expirationDate, result = result)
    }
}