package pl.loziuu.ivms.model.repair.domain

import org.springframework.data.jpa.repository.JpaRepository

interface RepairRepository: JpaRepository<Repair, Long> {
}
