package pl.loziuu.ivms.vehicle.ports

import org.springframework.hateoas.ResourceSupport

interface VehicleSearchPort {
    fun getInsured(): List<ResourceSupport>
    fun getUninsured(): List<ResourceSupport>
}