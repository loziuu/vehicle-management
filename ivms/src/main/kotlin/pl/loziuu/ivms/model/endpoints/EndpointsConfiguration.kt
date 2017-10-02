package pl.loziuu.ivms.model.endpoints

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.loziuu.ivms.infrastructure.adapters.VehicleRestAdapter
import pl.loziuu.ivms.model.vehicle.domain.VehicleFacade

@Configuration
class EndpointsConfiguration {

    @Bean
    fun vehicleRestController(facade: VehicleFacade): VehicleRestController {
        return VehicleRestController(VehicleRestAdapter(facade))
    }
}