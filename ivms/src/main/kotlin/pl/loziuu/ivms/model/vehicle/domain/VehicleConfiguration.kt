package pl.loziuu.ivms.model.vehicle.domain

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.loziuu.ivms.model.insurance.domain.InsuranceService
import pl.loziuu.ivms.model.repair.domain.RepairService
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryRepository
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryService
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryServiceImpl

@Configuration
class VehicleConfiguration {

    @Bean
    fun vehicleQueryService(vehicleQueryRepository: VehicleQueryRepository): VehicleQueryService {
        return VehicleQueryServiceImpl(vehicleQueryRepository);
    }

    @Bean
    fun vehicleCommandService(repository: VehicleRepository): VehicleService {
        return VehicleServiceImpl(repository);
    }

    @Bean
    fun vehicleFacade(commandRepository: VehicleRepository,
                      queryRepository: VehicleQueryRepository,
                      insuranceService: InsuranceService,
                      repairService: RepairService): VehicleFacade {
        val command = VehicleServiceImpl(commandRepository)
        val query = VehicleQueryServiceImpl(queryRepository)
        return VehicleFacade(command, query, insuranceService, repairService)
    }
}