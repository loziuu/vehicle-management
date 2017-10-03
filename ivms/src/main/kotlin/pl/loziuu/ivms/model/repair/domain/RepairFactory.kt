package pl.loziuu.ivms.model.repair.domain

object RepairFactory {
    fun create(details: RepairDetails): Repair {
        return Repair(0, details, 0);
    }
}