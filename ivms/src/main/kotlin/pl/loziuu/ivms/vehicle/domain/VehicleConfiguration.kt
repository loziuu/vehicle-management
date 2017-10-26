package pl.loziuu.ivms.vehicle.domain

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.loziuu.ivms.vehicle.query.VehicleQueryRepository
import pl.loziuu.ivms.vehicle.query.VehicleQueryService
import pl.loziuu.ivms.vehicle.query.VehicleQueryServiceImpl

@Configuration
class VehicleConfiguration {

    @Bean
    fun vehicleQueryService(queryRepository: VehicleQueryRepository): VehicleQueryService {
        return VehicleQueryServiceImpl(queryRepository)
    }

    @Bean
    fun vehicleFacade(commandRepository: VehicleRepository,
                      queryRepository: VehicleQueryRepository): VehicleFacade {
        val command = VehicleServiceImpl(commandRepository)
        val query = VehicleQueryServiceImpl(queryRepository)
        return VehicleFacade(command, query)
    }
}