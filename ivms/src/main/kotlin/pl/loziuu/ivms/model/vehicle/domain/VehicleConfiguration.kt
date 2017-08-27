package pl.loziuu.ivms.model.vehicle.domain

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryRepository
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryService
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryServiceImpl

@Configuration
class VehicleConfiguration {

    @Bean
    fun vehicleService(repository: VehicleRepository): VehicleService = VehicleServiceImpl(repository)

    @Bean
    fun vehicleQueryService(repository: VehicleQueryRepository): VehicleQueryService = VehicleQueryServiceImpl(repository)
}