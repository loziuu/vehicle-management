package pl.loziuu.ivms.model.vehicle.domain

@javax.persistence.Entity
@javax.persistence.Table(name = "vehicle")
data class Vehicle(
        @javax.persistence.Id @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY) val id: Long = 0,
        val model: String = "",
        val manufacturer: String = "",
        val productionYear: Int = 0) {

    fun toDto(): VehicleDto = VehicleDto(this.id, this.model, this.manufacturer, this.productionYear)
}
