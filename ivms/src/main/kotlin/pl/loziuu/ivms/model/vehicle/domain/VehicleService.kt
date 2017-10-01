package pl.loziuu.ivms.model.vehicle.domain

interface VehicleService {
    fun add(details: VehicleDetails): Long
    fun delete(id: Long)
}