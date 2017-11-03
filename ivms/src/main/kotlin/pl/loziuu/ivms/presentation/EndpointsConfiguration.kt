package pl.loziuu.ivms.presentation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.loziuu.ivms.management.infrastructure.adapters.VehicleRestAdapter
import pl.loziuu.ivms.management.infrastructure.adapters.VehicleSearchAdapter
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleQueryService
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleService

@Configuration
class EndpointsConfiguration {

    @Bean
    fun vehicleRestController(command: VehicleService, query: VehicleQueryService): VehicleRestController {
        return VehicleRestController(VehicleRestAdapter(command, query))
    }

    @Bean
    fun searchRestController(queryService: VehicleQueryService): SearchRestController {
        return SearchRestController(VehicleSearchAdapter(queryService))
    }
}