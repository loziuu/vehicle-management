package pl.loziuu.ivms.insurance.domain

import javax.persistence.Embeddable

@Embeddable
data class Company(val name: String = "")