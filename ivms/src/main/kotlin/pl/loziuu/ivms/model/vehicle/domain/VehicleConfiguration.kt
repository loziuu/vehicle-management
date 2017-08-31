package pl.loziuu.ivms.model.vehicle.domain

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.loziuu.ivms.model.insurance.domain.InsuranceService
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryRepository
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryServiceImpl

@Configuration
class VehicleConfiguration {

    @Bean
    fun vehicleFacade(commandRepository: VehicleRepository,
                      queryRepository: VehicleQueryRepository,
                      insuranceService: InsuranceService): VehicleFacade {
        val command = VehicleServiceImpl(commandRepository)
        val query = VehicleQueryServiceImpl(queryRepository)
        return VehicleFacade(command, query, insuranceService)
    }
}