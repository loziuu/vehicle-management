package pl.loziuu.ivms.management.vehicle.ports.primary

import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails

interface VehicleService {
    fun add(details: VehicleDetails): Long
    fun delete(id: Long)
}