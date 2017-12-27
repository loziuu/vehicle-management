package pl.loziuu.ivms.management.fleet.domain

import pl.loziuu.ivms.ddd.DomainValidationException

object FleetFactory {
    fun create(name: String): Fleet {
        if(name.trim().length <= 0) {
            throw DomainValidationException("Fleet name can't be empty")
        }
        return Fleet(name = name)
    }
}