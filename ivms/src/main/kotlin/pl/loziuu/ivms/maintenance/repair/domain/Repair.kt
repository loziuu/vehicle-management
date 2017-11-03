package pl.loziuu.ivms.maintenance.repair.domain

import javax.persistence.*

@Entity
@Table(name = "repair")
class Repair(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
             val id: Long = 0,
             val details: RepairDetails = RepairDetails(),
             var journalId: Long = 0) {

    fun getCost(): Double = details.cost
}
