package pl.loziuu.ivms.model.repair.domain

import pl.loziuu.ivms.infrastructure.exceptions.ValidationException
import java.math.BigDecimal

object RepairFactory {
    fun create(details: RepairDetails): Repair {
        validate(details)
        return Repair(details = details);
    }

    private fun validate(details: RepairDetails) {
        if (details.cost < 0)
            throw ValidationException()
    }
}