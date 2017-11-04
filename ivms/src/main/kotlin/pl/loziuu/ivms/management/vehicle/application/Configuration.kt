package pl.loziuu.ivms.management.vehicle.application

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.loziuu.ivms.management.vehicle.domain.VehicleServiceImpl
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleQueryService
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleService
import pl.loziuu.ivms.management.vehicle.ports.secondary.VehicleQueryRepository
import pl.loziuu.ivms.management.vehicle.ports.secondary.VehicleRepository
import pl.loziuu.ivms.management.vehicle.query.VehicleQueryServiceImpl

@Configuration
class Configuration {

    @Bean
    fun vehicleService(repository: VehicleRepository): VehicleService {
        return VehicleServiceImpl(repository)
    }

    @Bean
    fun vehicleQueryService(queryRepository: VehicleQueryRepository): VehicleQueryService {
        return VehicleQueryServiceImpl(queryRepository)
    }
}