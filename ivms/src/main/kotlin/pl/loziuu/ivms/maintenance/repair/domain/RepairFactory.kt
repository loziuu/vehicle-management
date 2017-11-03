package pl.loziuu.ivms.maintenance.repair.domain

import pl.loziuu.ivms.management.infrastructure.exceptions.ValidationException

object RepairFactory {
    fun create(details: RepairDetails): Repair {
        validate(details)
        return Repair(details = details)
    }

    private fun validate(details: RepairDetails) {
        if (details.cost < 0)
            throw ValidationException()
    }
}