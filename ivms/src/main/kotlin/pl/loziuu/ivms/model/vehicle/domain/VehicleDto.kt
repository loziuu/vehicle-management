package pl.loziuu.ivms.model.vehicle.domain

class VehicleDto(
        val id: Long = 0,
        val model: String = "",
        val manufacturer: String = "",
        val productionYear: Int = 0) {

    fun toEntity(): Vehicle = Vehicle(this.id, this.model, this.manufacturer, this.productionYear)
}

