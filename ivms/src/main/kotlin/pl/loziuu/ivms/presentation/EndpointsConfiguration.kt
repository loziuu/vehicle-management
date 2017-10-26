package pl.loziuu.ivms.presentation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.loziuu.ivms.infrastructure.adapters.VehicleRestAdapter
import pl.loziuu.ivms.infrastructure.adapters.VehicleSearchAdapter
import pl.loziuu.ivms.vehicle.domain.VehicleFacade
import pl.loziuu.ivms.vehicle.ports.primary.VehicleQueryService

@Configuration
class EndpointsConfiguration {

    @Bean
    fun vehicleRestController(facade: VehicleFacade): VehicleRestController {
        return VehicleRestController(VehicleRestAdapter(facade))
    }

    @Bean
    fun searchRestController(queryService: VehicleQueryService): SearchRestController {
        return SearchRestController(VehicleSearchAdapter(queryService))
    }
}