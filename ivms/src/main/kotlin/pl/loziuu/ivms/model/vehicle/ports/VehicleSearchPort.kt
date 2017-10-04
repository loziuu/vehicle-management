package pl.loziuu.ivms.model.vehicle.ports

import org.springframework.hateoas.ResourceSupport

interface VehicleSearchPort {
    fun getInsured(): List<ResourceSupport>
    fun getUninsured(): List<ResourceSupport>
}