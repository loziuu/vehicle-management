package pl.loziuu.ivms.ddd.infrastructure.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class ValidationException : RuntimeException()