package pl.loziuu.ivms.management.vehicle.ports.primary

import org.springframework.hateoas.ResourceSupport

interface VehicleSearchPort {
    fun getInsured(): List<ResourceSupport>
    fun getUninsured(): List<ResourceSupport>
}