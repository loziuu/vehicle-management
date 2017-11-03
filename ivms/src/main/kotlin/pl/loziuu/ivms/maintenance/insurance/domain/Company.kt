package pl.loziuu.ivms.maintenance.insurance.domain

import javax.persistence.Embeddable

@Embeddable
data class Company(val name: String = "")